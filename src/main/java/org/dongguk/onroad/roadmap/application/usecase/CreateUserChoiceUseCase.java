package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.CreateUserChoiceRequestDto;

import java.util.UUID;

@UseCase
public interface CreateUserChoiceUseCase {
    /**
     * 사용자의 문제 풀이를 생성하는 유스케이스
     */
    void execute(UUID userId, CreateUserChoiceRequestDto requestDto);
}
