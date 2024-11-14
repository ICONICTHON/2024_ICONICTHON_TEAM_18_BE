package org.dongguk.onroad.roadmap.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Builder
public record PdfEvent(

        Long lectureId,

        MultipartFile file
) {
    public Map<String, Object> toPayload() {
        return Map.of(
                "lectureId", lectureId,
                "pdf", file
        );
    }
}
