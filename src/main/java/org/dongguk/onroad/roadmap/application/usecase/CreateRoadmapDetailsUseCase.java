package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.kafka.CreateRoadmapDetailsKafkaResponseDto;

@UseCase
public interface CreateRoadmapDetailsUseCase {

    /**
     * Kafka로부터 Roadmap 상세 정보를 받아서 처리하는 유스케이스
     * @param responseDto CreateRoadmapDetailsKafkaResponseDto
     */
    void execute(
            CreateRoadmapDetailsKafkaResponseDto responseDto
    );
}
