package org.dongguk.onroad.roadmap.application.dto.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateRoadmapDetailsKafkaResponseDto(

        @NotNull(message = "Lecture ID는 필수 입력 값입니다.")
        @JsonProperty("lecture_id")
        Long lectureId,

        @NotNull(message = "제목은 필수 입력 값입니다.")
        @Size(max = 255, message = "제목은 최대 255자까지 입력 가능합니다.")
        @JsonProperty("title")
        String title,

        @NotNull(message = "전체 요약은 필수 입력 값입니다.")
        @Size(max = 1000, message = "전체 요약은 최대 1000자까지 입력 가능합니다.")
        @JsonProperty("overall_summary")
        String overallSummary,

        @NotNull(message = "섹션 목록은 필수 입력 값입니다.")
        @JsonProperty("sections")
        List<Section> sections

) {

    public record Section(

            @NotNull(message = "섹션 제목은 필수 입력 값입니다.")
            @JsonProperty("title")
            String title,

            @NotNull(message = "섹션 설명은 필수 입력 값입니다.")
            @JsonProperty("description")
            String description,

            @NotNull(message = "하위 주제 목록은 필수 입력 값입니다.")
            @JsonProperty("subtopics")
            List<Subtopic> subtopics

    ) {}

    public record Subtopic(

            @NotNull(message = "하위 주제 제목은 필수 입력 값입니다.")
            @JsonProperty("title")
            String title,

            @NotNull(message = "하위 주제 세부 설명은 필수 입력 값입니다.")
            @JsonProperty("detail")
            String detail,

            @NotNull(message = "체크포인트 목록은 필수 입력 값입니다.")
            @JsonProperty("checkpoints")
            List<String> checkpoints

    ) {}
}
