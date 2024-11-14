package org.dongguk.onroad.question.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.question.application.dto.request.CreateReplyRequestDto;
import org.dongguk.onroad.question.application.usecase.CreateReplyUseCase;
import org.dongguk.onroad.question.domain.Question;
import org.dongguk.onroad.question.domain.Reply;
import org.dongguk.onroad.question.domain.service.ReplyService;
import org.dongguk.onroad.question.repository.QuestionRepository;
import org.dongguk.onroad.question.repository.ReplyRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateReplyService implements CreateReplyUseCase {

    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final QuestionRepository questionRepository;

    private final ReplyService replyService;

    @Override
    @Transactional
    public void execute(UUID userId, Long questionId, CreateReplyRequestDto requestDto) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 질문 조회
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 답변 생성
        Reply reply = replyService.createReply(user, question, requestDto.content());
        replyRepository.save(reply);
    }
}
