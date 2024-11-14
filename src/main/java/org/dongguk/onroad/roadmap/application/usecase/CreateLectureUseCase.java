package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.CreateLectureRequestDto;

import java.util.UUID;

@UseCase
public interface CreateLectureUseCase {

    void execute(
            UUID userId,
            CreateLectureRequestDto requestDto
    );
}
