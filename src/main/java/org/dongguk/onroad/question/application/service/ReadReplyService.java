package org.dongguk.onroad.question.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.question.application.dto.response.ReadReplyResponseDto;
import org.dongguk.onroad.question.application.usecase.ReadReplyUseCase;
import org.dongguk.onroad.question.domain.Reply;
import org.dongguk.onroad.question.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadReplyService implements ReadReplyUseCase {

    private final ReplyRepository replyRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadReplyResponseDto execute(Long questionId) {
        List<Reply> replies = replyRepository.findAllByQuestionId(questionId);

        return ReadReplyResponseDto.of(replies.stream()
                .map(ReadReplyResponseDto.ReplyDto::fromEntity)
                .toList());
    }
}
