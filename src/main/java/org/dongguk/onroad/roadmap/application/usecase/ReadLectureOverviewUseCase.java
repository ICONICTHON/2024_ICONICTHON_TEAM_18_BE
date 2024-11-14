package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLectureOverviewResponseDto;

import java.util.UUID;

@UseCase
public interface ReadLectureOverviewUseCase {

    /**
     * 3.6 강의 개요 정보를 읽어오는 유스케이스
     * @param page 페이지
     * @param size 사이즈
     * @param userId 사용자 ID
     * @return ReadLectureOverviewResponseDto
     */
    ReadLectureOverviewResponseDto execute(
            Integer page,
            Integer size,
            UUID userId
    );

}
