package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.ReadLectureOverviewResponseDto;

import java.util.UUID;

@UseCase
public interface ReadLectureOverviewUseCase {

    ReadLectureOverviewResponseDto execute(
            Integer page,
            Integer size,
            UUID userId
    );

}
