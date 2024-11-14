package org.dongguk.onroad.question.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateQuestionRequestDto(
        @JsonProperty("lecture_id")
        Long lectureId,

        @JsonProperty("week")
        Integer week,

        @JsonProperty("title")
        String title,

        @JsonProperty("content")
        String content
) {
}
