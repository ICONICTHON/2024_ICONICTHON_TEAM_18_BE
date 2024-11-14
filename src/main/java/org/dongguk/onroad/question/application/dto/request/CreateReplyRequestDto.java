package org.dongguk.onroad.question.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateReplyRequestDto(
        @JsonProperty("content")
        String content
) {
}
