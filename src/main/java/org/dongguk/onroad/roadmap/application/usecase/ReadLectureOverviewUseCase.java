package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.ReadLectureOverviewResponseDto;

import java.util.UUID;

@UseCase
public interface ReadLectureOverviewUseCase {

    /**
     * 강의 개요 정보를 읽어오는 유스케이스
     */
    ReadLectureOverviewResponseDto execute(
            Integer page,
            Integer size,
            UUID userId
    );

}
