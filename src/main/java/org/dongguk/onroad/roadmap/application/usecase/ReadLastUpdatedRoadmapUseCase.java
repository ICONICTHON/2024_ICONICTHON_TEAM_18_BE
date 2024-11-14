package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLastUpdatedRoadmapResponseDto;

import java.util.UUID;

@UseCase
public interface ReadLastUpdatedRoadmapUseCase {
    /**
     * 3.9 Roadmap에서 가장 최근에 업데이트된 Roadmap을 읽어오는 유스케이스
     * @param userId 사용자 ID
     * @return ReadLastUpdatedRoadmapResponseDto
     */
    ReadLastUpdatedRoadmapResponseDto execute(UUID userId);
}
