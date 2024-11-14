package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLectureBriefResponseDto;

import java.util.UUID;

@UseCase
public interface ReadLectureBriefUseCase {

    /**
     * 3.1 강의 요약 정보(강의 이름 리스트)를 읽어오는 유스케이스
     * @param userId 사용자 ID
     * @return ReadLectureBriefResponseDto
     */
    ReadLectureBriefResponseDto execute(UUID userId);
}
