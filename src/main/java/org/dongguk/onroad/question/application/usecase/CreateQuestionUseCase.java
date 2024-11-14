package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.request.CreateQuestionRequestDto;

import java.util.UUID;

@UseCase
public interface CreateQuestionUseCase {

    /**
     * 질문을 생성하는 유스케이스
     * @param userId 사용자 ID
     * @param requestDto CreateQuestionRequestDto
     */
    void execute(UUID userId, CreateQuestionRequestDto requestDto);

}
