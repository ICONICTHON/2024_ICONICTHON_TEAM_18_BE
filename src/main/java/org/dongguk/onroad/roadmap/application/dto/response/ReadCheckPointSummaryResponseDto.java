package org.dongguk.onroad.roadmap.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.roadmap.domain.CheckPoint;

import java.util.List;

@Getter
public class ReadCheckPointSummaryResponseDto extends SelfValidating<ReadCheckPointSummaryResponseDto> {

    @JsonProperty("title")
    @NotNull(message = "title은 null일 수 없습니다.")
    private final String title;

    @JsonProperty("detail")
    @NotNull(message = "detail은 null일 수 없습니다.")
    private final String detail;

    @JsonProperty("check_point_list")
    @NotNull(message = "check_point_list는 null일 수 없습니다.")
    private final List<CheckPointDto> checkPointList;

    @Builder
    public ReadCheckPointSummaryResponseDto(String title, String detail, List<CheckPoint> checkPointList) {
        this.title = title;
        this.detail = detail;
        this.checkPointList = CheckPointDto.fromEntities(checkPointList);
        this.validateSelf();
    }

    @Getter
    public static class CheckPointDto extends SelfValidating<CheckPointDto> {

        @JsonProperty("id")
        @NotNull(message = "id는 null일 수 없습니다.")
        private final Long id;

        @JsonProperty("content")
        @NotNull(message = "content는 null일 수 없습니다.")
        private final String content;

        @Builder
        public CheckPointDto(Long id, String content) {
            this.id = id;
            this.content = content;
            this.validateSelf();
        }

        public static List<CheckPointDto> fromEntities(List<CheckPoint> checkPointList) {
            return checkPointList.stream()
                    .map(checkPoint -> CheckPointDto.builder()
                            .id(checkPoint.getId())
                            .content(checkPoint.getContent())
                            .build())
                    .toList();
        }
    }

    public static ReadCheckPointSummaryResponseDto of(String title, String detail, List<CheckPoint> checkPointList) {
        return ReadCheckPointSummaryResponseDto.builder()
                .title(title)
                .detail(detail)
                .checkPointList(checkPointList)
                .build();
    }
}
