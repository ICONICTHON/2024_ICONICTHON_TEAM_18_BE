package org.dongguk.onroad.roadmap.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateUserChoiceRequestDto(

        @NotNull
        @JsonProperty("user_quiz_list")
        List<UserQuiz> userQuizList

) {

    public record UserQuiz(
            @NotNull
            @JsonProperty("quiz_id")
            Long quizId,

            @NotNull
            @JsonProperty("choice_id")
            Long choiceId
    ) {}
}
