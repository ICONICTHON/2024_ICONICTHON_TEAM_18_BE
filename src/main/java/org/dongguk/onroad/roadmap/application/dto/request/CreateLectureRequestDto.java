package org.dongguk.onroad.roadmap.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.dongguk.onroad.roadmap.domain.type.ESemester;

public record CreateLectureRequestDto(

        @NotNull(message = "제목은 필수 입력 값입니다.")
        @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
        @JsonProperty("title")
        String title,

        @NotNull(message = "학기는 필수 입력 값입니다.")
        @JsonProperty("semester")
        ESemester semester
)
{}
