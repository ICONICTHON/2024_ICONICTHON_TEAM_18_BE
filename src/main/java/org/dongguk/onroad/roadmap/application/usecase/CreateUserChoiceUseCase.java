package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.CreateUserChoiceRequestDto;

import java.util.UUID;

@UseCase
public interface CreateUserChoiceUseCase {
    /**
     * 3.5 퀴즈 정답을 입력하는 유스케이스
     * @param userId 사용자 ID
     * @param requestDto CreateUserChoiceRequestDto
     */
    void execute(UUID userId, CreateUserChoiceRequestDto requestDto);
}
