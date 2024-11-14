package org.dongguk.onroad.roadmap.application.controller.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dongguk.onroad.roadmap.application.dto.kafka.CreateRoadmapDetailsKafkaResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapDetailsUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@RequiredArgsConstructor
public class RoadmapKafkaConsumerV1Controller{

    private final ObjectMapper objectMapper;
    private final CreateRoadmapDetailsUseCase createRoadmapDetailsUseCase;

    @KafkaListener(
        topics = "ai.api",
        groupId = "api-server"
    )
    public void consumePdfEvent(
        ConsumerRecord<String, Map<String, Object>> record
    ) {
        Map<String, Object> payload = record.value();

        CreateRoadmapDetailsKafkaResponseDto createRoadmapDetailsKafkaResponseDto = objectMapper.convertValue(payload, CreateRoadmapDetailsKafkaResponseDto.class);

        createRoadmapDetailsUseCase.execute(
            createRoadmapDetailsKafkaResponseDto
        );
    }
}

