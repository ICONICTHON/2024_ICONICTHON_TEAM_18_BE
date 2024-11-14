package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.response.ReadReplyResponseDto;

import java.util.UUID;

@UseCase
public interface ReadReplyUseCase {

    /**
     * 질문에 대한 답변을 읽는 유스케이스
     * @param userId 사용자 ID
     * @param questionId 질문 ID
     * @return ReadReplyResponseDto 읽어온 답변
     */
    ReadReplyResponseDto execute(UUID userId, Long questionId);
}
