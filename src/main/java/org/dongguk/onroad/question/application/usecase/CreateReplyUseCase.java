package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.request.CreateReplyRequestDto;

import java.util.UUID;

@UseCase
public interface CreateReplyUseCase {

    /**
     * 질문에 대한 답변을 생성하는 유스케이스
     * @param userId 사용자 ID
     * @param questionId 질문 ID
     * @param requestDto CreateReplyRequestDto
     */
    void execute(UUID userId, Long questionId, CreateReplyRequestDto requestDto);
}
