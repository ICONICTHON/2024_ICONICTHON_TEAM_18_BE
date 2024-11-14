package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadRoadmapResponseDto;

@UseCase
public interface ReadRoadmapUseCase {
    /**
     * Roadmap을 읽는 유스케이스
     * @param lectureId Roadmap ID
     * @return RoadmapDto
     */
    ReadRoadmapResponseDto execute(Long lectureId);
}
