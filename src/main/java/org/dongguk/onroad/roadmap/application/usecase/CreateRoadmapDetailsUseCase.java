package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.kafka.CreateRoadmapDetailsKafkaResponseDto;

@UseCase
public interface CreateRoadmapDetailsUseCase {

    void execute(
            CreateRoadmapDetailsKafkaResponseDto responseDto
    );
}
