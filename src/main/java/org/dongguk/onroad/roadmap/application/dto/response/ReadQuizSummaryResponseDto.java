package org.dongguk.onroad.roadmap.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.roadmap.domain.Choice;

import java.util.List;

@Getter
public class ReadQuizSummaryResponseDto extends SelfValidating<ReadQuizSummaryResponseDto> {

    @JsonProperty("quiz_list")
    @NotNull(message = "quiz_list는 null일 수 없습니다.")
    private final List<QuizDto> quizList;

    @Builder
    public ReadQuizSummaryResponseDto(List<QuizDto> quizList) {
        this.quizList = quizList;
        this.validateSelf();
    }

    @Getter
    public static class QuizDto extends SelfValidating<QuizDto> {

        @JsonProperty("id")
        @NotNull(message = "id는 null일 수 없습니다.")
        private final Long id;

        @JsonProperty("content")
        @NotNull(message = "content는 null일 수 없습니다.")
        private final String content;

        @JsonProperty("choice_list")
        @NotNull(message = "choice_list는 null일 수 없습니다.")
        private final List<ChoiceDto> choiceList;

        @Builder
        public QuizDto(Long id, String content, List<ChoiceDto> choiceList) {
            this.id = id;
            this.content = content;
            this.choiceList = choiceList;
            this.validateSelf();
        }

        public static QuizDto of(Long id, String content, List<ChoiceDto> choiceList) {
            return QuizDto.builder()
                    .id(id)
                    .content(content)
                    .choiceList(choiceList)
                    .build();
        }
    }

    @Getter
    public static class ChoiceDto extends SelfValidating<ChoiceDto> {

        @JsonProperty("id")
        @NotNull(message = "id는 null일 수 없습니다.")
        private final Long id;

        @JsonProperty("content")
        @NotNull(message = "content는 null일 수 없습니다.")
        private final String content;

        @JsonProperty("is_answer")
        @NotNull(message = "is_answer는 null일 수 없습니다.")
        private final Boolean isAnswer;

        @Builder
        public ChoiceDto(Long id, String content, Boolean isAnswer) {
            this.id = id;
            this.content = content;
            this.isAnswer = isAnswer;
            this.validateSelf();
        }

        public static ChoiceDto fromEntity(Choice choice) {
            return ChoiceDto.builder()
                    .id(choice.getId())
                    .content(choice.getContent())
                    .isAnswer(choice.getIsAnswer())
                    .build();
        }
    }

    public static ReadQuizSummaryResponseDto of(List<QuizDto> quizList) {
        return ReadQuizSummaryResponseDto.builder()
                .quizList(quizList)
                .build();
    }
}
