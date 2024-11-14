package org.dongguk.onroad.roadmap.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.dongguk.onroad.core.dto.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.dongguk.onroad.roadmap.domain.Lecture;

import java.util.List;

@Getter
public class ReadLectureBriefResponseDto extends SelfValidating<ReadLectureBriefResponseDto> {

    @JsonProperty("lecture_list")
    private final List<LectureDto> lectureList;

    @Builder
    public ReadLectureBriefResponseDto(List<LectureDto> lectureList) {
        this.lectureList = lectureList;
        this.validateSelf();
    }

    @Getter
    public static class LectureDto extends SelfValidating<LectureDto> {

        @NotNull(message = "id는 null일 수 없습니다.")
        private final Long id;

        @NotNull(message = "title은 null일 수 없습니다.")
        private final String title;

        @NotNull(message = "status는 null일 수 없습니다.")
        private final String status;

        @Builder
        public LectureDto(Long id, String title, String status) {
            this.id = id;
            this.title = title;
            this.status = status;

            this.validateSelf();
        }

        public static LectureDto fromEntity(Lecture lecture) {
            return LectureDto.builder()
                    .id(lecture.getId())
                    .title(lecture.getTitle())
                    .status(lecture.getStatus().toString())
                    .build();
        }
    }

    public static ReadLectureBriefResponseDto fromEntities(List<Lecture> lectureList) {
        List<LectureDto> lectureDtoList = lectureList.stream()
                .map(LectureDto::fromEntity)
                .toList();

        return ReadLectureBriefResponseDto.builder()
                .lectureList(lectureDtoList)
                .build();
    }
}
