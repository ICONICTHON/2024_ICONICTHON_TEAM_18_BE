package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLectureBriefResponseDto;

import java.util.UUID;

@UseCase
public interface ReadLectureBriefUseCase {

    /**
     * 강의 요약 정보를 읽어오는 유스케이스
     */
    ReadLectureBriefResponseDto execute(UUID userId);
}
