package org.dongguk.onroad.question.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.core.utility.DateTimeUtil;
import org.dongguk.onroad.question.domain.Question;

import java.util.List;

@Getter
public class ReadQuestionResponseDto extends SelfValidating<ReadQuestionResponseDto> {

    @JsonProperty("question_list")
    @NotNull(message = "question_list는 null일 수 없습니다.")
    private final List<QuestionDto> questionList;

    @Builder
    public ReadQuestionResponseDto(List<QuestionDto> questionList) {
        this.questionList = questionList;
        this.validateSelf();
    }

    @Getter
    public static class QuestionDto extends SelfValidating<QuestionDto> {

        @JsonProperty("question_info")
        @NotNull(message = "question_info는 null일 수 없습니다.")
        private final QuestionInfoDto questionInfo;

        @JsonProperty("reply_count")
        @NotNull(message = "reply_count는 null일 수 없습니다.")
        private final Integer replyCount;

        @Builder
        public QuestionDto(QuestionInfoDto questionInfo, Integer replyCount) {
            this.questionInfo = questionInfo;
            this.replyCount = replyCount;
            this.validateSelf();
        }

        public static QuestionDto of(QuestionInfoDto questionInfo, Integer replyCount) {
            return QuestionDto.builder()
                    .questionInfo(questionInfo)
                    .replyCount(replyCount)
                    .build();
        }
    }

    @Getter
    public static class QuestionInfoDto extends SelfValidating<QuestionInfoDto> {

        @JsonProperty("writer_name")
        @NotNull(message = "writer_name은 null일 수 없습니다.")
        private final String writerName;

        @JsonProperty("created_at")
        @NotNull(message = "created_at은 null일 수 없습니다.")
        private final String createdAt;

        @JsonProperty("title")
        @NotNull(message = "title은 null일 수 없습니다.")
        private final String title;

        @JsonProperty("content")
        @NotNull(message = "content는 null일 수 없습니다.")
        private final String content;

        @Builder
        public QuestionInfoDto(String writerName, String createdAt, String title, String content) {
            this.writerName = writerName;
            this.createdAt = createdAt;
            this.title = title;
            this.content = content;
            this.validateSelf();
        }

        public static QuestionInfoDto fromEntity(Question question) {
            return QuestionInfoDto.builder()
                    .writerName(question.getStudent().getName())
                    .createdAt(DateTimeUtil.convertLocalDateTimeToString(question.getCreatedAt()))
                    .title(question.getTitle())
                    .content(question.getContent())
                    .build();
        }
    }

    public static ReadQuestionResponseDto of(List<QuestionDto> questionList) {
        return ReadQuestionResponseDto.builder()
                .questionList(questionList)
                .build();
    }
}
