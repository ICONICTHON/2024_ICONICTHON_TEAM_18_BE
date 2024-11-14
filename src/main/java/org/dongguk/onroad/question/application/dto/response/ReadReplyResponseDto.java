package org.dongguk.onroad.question.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.core.utility.DateTimeUtil;
import org.dongguk.onroad.question.domain.Reply;

import java.util.List;

@Getter
public class ReadReplyResponseDto extends SelfValidating<ReadReplyResponseDto> {

    @JsonProperty("reply_list")
    @NotNull(message = "reply_list는 null일 수 없습니다.")
    private final List<ReplyDto> replyList;

    @Builder
    public ReadReplyResponseDto(List<ReplyDto> replyList) {
        this.replyList = replyList;
        this.validateSelf();
    }

    @Getter
    public static class ReplyDto extends SelfValidating<ReplyDto> {

        @JsonProperty("writer_name")
        @NotNull(message = "writer_name은 null일 수 없습니다.")
        private final String writerName;

        @JsonProperty("created_at")
        @NotNull(message = "created_at은 null일 수 없습니다.")
        private final String createdAt;

        @JsonProperty("content")
        @NotNull(message = "content는 null일 수 없습니다.")
        private final String content;

        @Builder
        public ReplyDto(String writerName, String createdAt, String content) {
            this.writerName = writerName;
            this.createdAt = createdAt;
            this.content = content;
            this.validateSelf();
        }

        public static ReplyDto fromEntityProfessor(Reply reply) {
            return ReplyDto.builder()
                    .writerName(reply.getUser().getName())
                    .createdAt(DateTimeUtil.convertLocalDateTimeToString(reply.getCreatedAt()))
                    .content(reply.getContent())
                    .build();
        }

        public static ReplyDto fromEntityStudent(Reply reply) {
            return ReplyDto.builder()
                    .writerName("anonymous")
                    .createdAt(DateTimeUtil.convertLocalDateTimeToString(reply.getCreatedAt()))
                    .content(reply.getContent())
                    .build();
        }
    }

    public static ReadReplyResponseDto of(List<ReplyDto> replyList) {
        return ReadReplyResponseDto.builder()
                .replyList(replyList)
                .build();
    }
}
