package org.dongguk.onroad.roadmap.application.event;

import lombok.Builder;

import java.util.Map;

@Builder
public record PdfEvent(
        Long lectureId,
        String fileName
) {
    public Map<String, Object> toPayload() {
        return Map.of(
                "lectureId", lectureId,
                "fileName", fileName
        );
    }
}
