package org.dongguk.onroad.question.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.question.application.dto.response.ReadReplyResponseDto;
import org.dongguk.onroad.question.application.usecase.ReadReplyUseCase;
import org.dongguk.onroad.question.domain.Reply;
import org.dongguk.onroad.question.repository.ReplyRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadReplyService implements ReadReplyUseCase {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadReplyResponseDto execute(UUID userId, Long questionId) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 학생인 경우 다른 유저들의 이름을 익명으로 제공
        if (user.getRole() == ESecurityRole.STUDENT) {
            List<Reply> replies = replyRepository.findAllByQuestionId(questionId);

            return ReadReplyResponseDto.of(replies.stream()
                    .map(ReadReplyResponseDto.ReplyDto::fromEntityStudent)
                    .toList());
        }

        // 교수인 경우 모든 유저들의 이름을 제공
        List<Reply> replies = replyRepository.findAllByQuestionId(questionId);

        return ReadReplyResponseDto.of(replies.stream()
                .map(ReadReplyResponseDto.ReplyDto::fromEntityProfessor)
                .toList());
    }
}
