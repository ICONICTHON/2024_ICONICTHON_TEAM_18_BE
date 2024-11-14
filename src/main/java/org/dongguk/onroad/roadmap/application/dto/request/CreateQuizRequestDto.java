package org.dongguk.onroad.roadmap.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateQuizRequestDto(

        @NotNull(message = "퀴즈 목록은 필수 입력 값입니다.")
        @JsonProperty("quiz_list")
        List<Quiz> quizList

) {
    public record Quiz(

            @NotNull(message = "check Point id는 필수 입력 값입니다.")
            @JsonProperty("id")
            Long id,

            @NotNull(message = "퀴즈 내용은 필수 입력 값입니다.")
            @JsonProperty("content")
            String content,

            @NotNull(message = "선택지 목록은 필수 입력 값입니다.")
            @Size(min = 3, max = 3, message = "선택지는 반드시 3개여야 합니다.")
            @JsonProperty("choice_list")
            List<Choice> choiceList

    ) {}

    public record Choice(

            @NotNull(message = "선택지 내용은 필수 입력 값입니다.")
            @JsonProperty("content")
            String content,

            @NotNull(message = "정답 여부는 필수 입력 값입니다.")
            @JsonProperty("is_answer")
            Boolean isAnswer

    ) {}
}
