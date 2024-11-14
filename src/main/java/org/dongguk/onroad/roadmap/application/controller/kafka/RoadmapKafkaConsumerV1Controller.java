package org.dongguk.onroad.roadmap.application.controller.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.dongguk.onroad.roadmap.application.dto.kafka.CreateRoadmapDetailsKafkaResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapDetailsUseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoadmapKafkaConsumerV1Controller {

    private final ObjectMapper objectMapper;
    private final CreateRoadmapDetailsUseCase createRoadmapDetailsUseCase;

    @KafkaListener(
            topics = "ai.api",
            groupId = "api-server"
    )
    public void consumePdfEvent(
            ConsumerRecord<String, Object> record
    ) {
        Object messageValue = record.value();

        try {
            String jsonPayload;
            if (messageValue instanceof LinkedHashMap) {
                jsonPayload = objectMapper.writeValueAsString(messageValue);
            } else {
                jsonPayload = messageValue.toString();
            }

            CreateRoadmapDetailsKafkaResponseDto createRoadmapDetailsKafkaResponseDto =
                    objectMapper.readValue(jsonPayload, CreateRoadmapDetailsKafkaResponseDto.class);

            createRoadmapDetailsUseCase.execute(createRoadmapDetailsKafkaResponseDto);

        } catch (JsonProcessingException e) {
            log.error("Failed to parse Kafka message to CreateRoadmapDetailsKafkaResponseDto", e);
        }
    }
}
