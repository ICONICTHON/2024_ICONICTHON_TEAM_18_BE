package org.dongguk.onroad.roadmap.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.roadmap.domain.Week;

import java.util.List;

@Getter
public class ReadLectureOverviewResponseDto extends SelfValidating<ReadLectureOverviewResponseDto> {

    @NotNull(message = "lecture_list는 null일 수 없습니다.")
    @JsonProperty("lecture_list")
    private final List<LectureOverview> lectureList;

    @NotNull(message = "total_page는 null일 수 없습니다.")
    @JsonProperty("total_page")
    private final Integer totalPage;

    @NotNull(message = "current_page는 null일 수 없습니다.")
    @JsonProperty("current_page")
    private final Integer currentPage;

    @Builder
    public ReadLectureOverviewResponseDto(List<LectureOverview> lectureList, Integer totalPage, Integer currentPage) {
        this.lectureList = lectureList;
        this.totalPage = totalPage;
        this.currentPage = currentPage;

        this.validateSelf();
    }

    public static ReadLectureOverviewResponseDto of(
            List<LectureOverview> lectureList,
            Integer totalPage,
            Integer currentPage) {

        return ReadLectureOverviewResponseDto.builder()
                .lectureList(lectureList)
                .totalPage(totalPage)
                .currentPage(currentPage)
                .build();
    }

    @Getter
    public static class LectureOverview extends SelfValidating<LectureOverview> {

        @NotNull(message = "id는 null일 수 없습니다.")
        @JsonProperty("id")
        private final Long id;

        @NotNull(message = "title은 null일 수 없습니다.")
        @JsonProperty("title")
        private final String title;

        @NotNull(message = "status는 null일 수 없습니다.")
        private final String status;

        @NotNull(message = "professor_name은 null일 수 없습니다.")
        @JsonProperty("professor_name")
        private final String professorName;

        @NotNull(message = "current_week는 null일 수 없습니다.")
        @JsonProperty("current_week")
        private final Integer currentWeek;

        @JsonProperty("progress_rate")
        private final Integer progressRate;

        @Builder
        public LectureOverview(
                Long id,
                String title,
                String status,
                String professorName,
                Integer currentWeek,
                Integer progressRate
        ) {
            this.id = id;
            this.title = title;
            this.status = status;
            this.professorName = professorName;
            this.currentWeek = currentWeek;
            this.progressRate = progressRate;

            this.validateSelf();
        }

        public static LectureOverview of(
                Lecture lecture,
                UserLecture userLecture,
                Week week,
                Integer progressRate) {

            return LectureOverview.builder()
                    .id(lecture.getId())
                    .title(lecture.getTitle())
                    .status(lecture.getStatus().toString())
                    .professorName(userLecture.getProfessor().getName())
                    .currentWeek(week.getWeekIndex())
                    .progressRate(progressRate)
                    .build();
        }
    }
}
