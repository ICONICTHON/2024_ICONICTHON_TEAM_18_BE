package org.dongguk.onroad.roadmap.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Builder
public record PdfEvent(
        Long lectureId,
        MultipartFile file
) {
    public Map<String, Object> toPayload() {
        try {
            // MultipartFile을 바이트 배열로 변환하여 직렬화가 가능하게 만듭니다.
            String fileContent = Base64.getEncoder().encodeToString(file.getBytes());
            return Map.of(
                    "lectureId", lectureId,
                    "pdf", fileContent
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize file content", e);
        }
    }
}
