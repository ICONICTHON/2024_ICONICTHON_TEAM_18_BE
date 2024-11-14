package org.dongguk.onroad.roadmap.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.Section;
import org.dongguk.onroad.roadmap.domain.Subtopic;
import org.dongguk.onroad.roadmap.domain.Week;

import java.util.List;

public class ReadLastUpdatedRoadmapResponseDto extends SelfValidating<ReadLastUpdatedRoadmapResponseDto> {

    @JsonProperty("lecture_info")
    private final ReadLastUpdatedRoadmapResponseDto.LectureInfoDto lectureInfo;

    @Builder
    public ReadLastUpdatedRoadmapResponseDto(ReadLastUpdatedRoadmapResponseDto.LectureInfoDto lectureInfo) {
        this.lectureInfo = lectureInfo;
        this.validateSelf();
    }

    @Getter
    public static class LectureInfoDto extends SelfValidating<ReadLastUpdatedRoadmapResponseDto.LectureInfoDto> {

        private final Long id;

        @JsonProperty("lecture_name")
        private final String lectureName;

        private final Integer year;

        private final String semester;

        @JsonProperty("professor_name")
        private final String professorName;

        @JsonProperty("week_info")
        private final List<ReadLastUpdatedRoadmapResponseDto.WeekInfoDto> weekInfo;

        @Builder
        public LectureInfoDto(Long id, String lectureName, Integer year, String semester, String professorName, List<ReadLastUpdatedRoadmapResponseDto.WeekInfoDto> weekInfo) {
            this.id = id;
            this.lectureName = lectureName;
            this.year = year;
            this.semester = semester;
            this.professorName = professorName;
            this.weekInfo = weekInfo;
            this.validateSelf();
        }

        public static ReadLastUpdatedRoadmapResponseDto.LectureInfoDto of(Lecture lecture, List<ReadLastUpdatedRoadmapResponseDto.WeekInfoDto> weekInfoDtos, String professorName) {
            return ReadLastUpdatedRoadmapResponseDto.LectureInfoDto.builder()
                    .id(lecture.getId())
                    .lectureName(lecture.getTitle())
                    .year(lecture.getYear())
                    .semester(lecture.getSemester().toString())
                    .professorName(professorName)
                    .weekInfo(weekInfoDtos)
                    .build();
        }

        public static ReadLastUpdatedRoadmapResponseDto.LectureInfoDto zero(Lecture lecture, String professorName) {
            return ReadLastUpdatedRoadmapResponseDto.LectureInfoDto.builder()
                    .id(lecture.getId())
                    .lectureName(lecture.getTitle())
                    .year(lecture.getYear())
                    .semester(lecture.getSemester().toString())
                    .professorName(professorName)
                    .weekInfo(null)
                    .build();

        }
    }

    @Getter
    public static class WeekInfoDto extends SelfValidating<ReadLastUpdatedRoadmapResponseDto.WeekInfoDto> {

        private final Long id;

        @JsonProperty("week_index")
        private final Integer weekIndex;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("is_selected_week")
        private final Boolean isSelectedWeek;

        @JsonProperty("overall_summary")
        private final String overallSummary;

        @JsonProperty("section_info")
        private final List<ReadLastUpdatedRoadmapResponseDto.SectionInfoDto> sectionInfo;

        @Builder
        public WeekInfoDto(Long id, Integer weekIndex, Boolean isSelectedWeek, String title, String overallSummary, List<ReadLastUpdatedRoadmapResponseDto.SectionInfoDto> sectionInfo) {
            this.id = id;
            this.weekIndex = weekIndex;
            this.isSelectedWeek = isSelectedWeek;
            this.title = title;
            this.overallSummary = overallSummary;
            this.sectionInfo = sectionInfo;
            this.validateSelf();
        }

        public static ReadLastUpdatedRoadmapResponseDto.WeekInfoDto of(Week week, List<ReadLastUpdatedRoadmapResponseDto.SectionInfoDto> sectionInfoDtos) {
            return ReadLastUpdatedRoadmapResponseDto.WeekInfoDto.builder()
                    .id(week.getId())
                    .weekIndex(week.getWeekIndex())
                    .isSelectedWeek(week.getIsSelected())
                    .title(week.getTitle())
                    .overallSummary(week.getOverallSummary())
                    .sectionInfo(sectionInfoDtos)
                    .build();
        }
    }

    @Getter
    public static class SectionInfoDto extends SelfValidating<ReadLastUpdatedRoadmapResponseDto.SectionInfoDto> {

        private final Long id;

        private final String title;

        private final String description;

        @JsonProperty("subtopic_info")
        private final List<ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto> subtopicInfo;

        @Builder
        public SectionInfoDto(Long id, String title, String description, List<ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto> subtopicInfo) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.subtopicInfo = subtopicInfo;
            this.validateSelf();
        }

        public static ReadLastUpdatedRoadmapResponseDto.SectionInfoDto of(Section section, List<ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto> subtopicInfoDtos) {
            return ReadLastUpdatedRoadmapResponseDto.SectionInfoDto.builder()
                    .id(section.getId())
                    .title(section.getTitle())
                    .description(section.getDescription())
                    .subtopicInfo(subtopicInfoDtos)
                    .build();
        }
    }

    @Getter
    public static class SubtopicInfoDto extends SelfValidating<ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto> {

        private final Long id;

        private final String title;

        private final String detail;

        @Builder
        public SubtopicInfoDto(Long id, String title, String detail) {
            this.id = id;
            this.title = title;
            this.detail = detail;
            this.validateSelf();
        }

        public static ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto fromEntity(Subtopic subtopic) {
            return ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto.builder()
                    .id(subtopic.getId())
                    .title(subtopic.getTitle())
                    .detail(subtopic.getDetail())
                    .build();
        }
    }

    public static ReadLastUpdatedRoadmapResponseDto of(Lecture lecture, List<ReadLastUpdatedRoadmapResponseDto.WeekInfoDto> weekInfoDtos, String professorName) {
        return ReadLastUpdatedRoadmapResponseDto.builder()
                .lectureInfo(ReadLastUpdatedRoadmapResponseDto.LectureInfoDto.of(lecture, weekInfoDtos, professorName))
                .build();
    }

    public static ReadLastUpdatedRoadmapResponseDto zero(Lecture lecture, String professorName) {
        return ReadLastUpdatedRoadmapResponseDto.builder()
                .lectureInfo(ReadLastUpdatedRoadmapResponseDto.LectureInfoDto.zero(lecture, professorName))
                .build();
    }
}
