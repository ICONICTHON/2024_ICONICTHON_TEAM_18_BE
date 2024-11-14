package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.CreateQuizRequestDto;

import java.util.UUID;

@UseCase
public interface CreateQuizUseCase {

    void execute(
            UUID userId,
            CreateQuizRequestDto requestDto
    );
}
