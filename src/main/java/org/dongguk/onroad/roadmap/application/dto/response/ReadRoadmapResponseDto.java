package org.dongguk.onroad.roadmap.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.dongguk.onroad.core.dto.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.Section;
import org.dongguk.onroad.roadmap.domain.Subtopic;
import org.dongguk.onroad.roadmap.domain.Week;

import java.util.List;

@Getter
public class ReadRoadmapResponseDto extends SelfValidating<ReadRoadmapResponseDto> {

    @JsonProperty("lecture_info")
    private final LectureInfoDto lectureInfo;

    @Builder
    public ReadRoadmapResponseDto(LectureInfoDto lectureInfo) {
        this.lectureInfo = lectureInfo;
        this.validateSelf();
    }

    @Getter
    public static class LectureInfoDto extends SelfValidating<LectureInfoDto> {

        private final Long id;

        @JsonProperty("lecture_name")
        private final String lectureName;

        private final Integer year;

        private final String semester;

        @JsonProperty("professor_name")
        private final String professorName;

        @JsonProperty("week_info")
        private final List<WeekInfoDto> weekInfo;

        @Builder
        public LectureInfoDto(Long id, String lectureName, Integer year, String semester, String professorName, List<WeekInfoDto> weekInfo) {
            this.id = id;
            this.lectureName = lectureName;
            this.year = year;
            this.semester = semester;
            this.professorName = professorName;
            this.weekInfo = weekInfo;
            this.validateSelf();
        }

        public static LectureInfoDto of(Lecture lecture, List<WeekInfoDto> weekInfoDtos, String professorName) {
            return LectureInfoDto.builder()
                    .id(lecture.getId())
                    .lectureName(lecture.getTitle())
                    .year(lecture.getYear())
                    .semester(lecture.getSemester().toString())
                    .professorName(professorName)
                    .weekInfo(weekInfoDtos)
                    .build();
        }

        public static LectureInfoDto zero(Lecture lecture, String professorName) {
            return LectureInfoDto.builder()
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
    public static class WeekInfoDto extends SelfValidating<WeekInfoDto> {

        private final Long id;

        @JsonProperty("week_index")
        private final Integer weekIndex;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("overall_summary")
        private final String overallSummary;

        @JsonProperty("section_info")
        private final List<SectionInfoDto> sectionInfo;

        @Builder
        public WeekInfoDto(Long id, Integer weekIndex, String title, String overallSummary, List<SectionInfoDto> sectionInfo) {
            this.id = id;
            this.weekIndex = weekIndex;
            this.title = title;
            this.overallSummary = overallSummary;
            this.sectionInfo = sectionInfo;
            this.validateSelf();
        }

        public static WeekInfoDto of(Week week, List<SectionInfoDto> sectionInfoDtos) {
            return WeekInfoDto.builder()
                    .id(week.getId())
                    .weekIndex(week.getWeekIndex())
                    .title(week.getTitle())
                    .overallSummary(week.getOverallSummary())
                    .sectionInfo(sectionInfoDtos)
                    .build();
        }
    }

    @Getter
    public static class SectionInfoDto extends SelfValidating<SectionInfoDto> {

        private final Long id;

        private final String title;

        private final String description;

        @JsonProperty("subtopic_info")
        private final List<SubtopicInfoDto> subtopicInfo;

        @Builder
        public SectionInfoDto(Long id, String title, String description, List<SubtopicInfoDto> subtopicInfo) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.subtopicInfo = subtopicInfo;
            this.validateSelf();
        }

        public static SectionInfoDto of(Section section, List<SubtopicInfoDto> subtopicInfoDtos) {
            return SectionInfoDto.builder()
                    .id(section.getId())
                    .title(section.getTitle())
                    .description(section.getDescription())
                    .subtopicInfo(subtopicInfoDtos)
                    .build();
        }
    }

    @Getter
    public static class SubtopicInfoDto extends SelfValidating<SubtopicInfoDto> {

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

        public static SubtopicInfoDto fromEntity(Subtopic subtopic) {
            return SubtopicInfoDto.builder()
                    .id(subtopic.getId())
                    .title(subtopic.getTitle())
                    .detail(subtopic.getDetail())
                    .build();
        }
    }

    public static ReadRoadmapResponseDto of(Lecture lecture, List<WeekInfoDto> weekInfoDtos, String professorName) {
        return ReadRoadmapResponseDto.builder()
                .lectureInfo(LectureInfoDto.of(lecture, weekInfoDtos, professorName))
                .build();
    }

    public static ReadRoadmapResponseDto zero(Lecture lecture, String professorName) {
        return ReadRoadmapResponseDto.builder()
                .lectureInfo(LectureInfoDto.zero(lecture, professorName))
                .build();
    }
}
