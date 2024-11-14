package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.response.ReadRoadmapResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadRoadmapUseCase;
import org.dongguk.onroad.roadmap.domain.*;
import org.dongguk.onroad.roadmap.domain.service.LectureService;
import org.dongguk.onroad.roadmap.domain.type.EStatus;
import org.dongguk.onroad.roadmap.repository.*;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadRoadmapService implements ReadRoadmapUseCase {

    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;
    private final LectureRepository lectureRepository;
    private final WeekRepository weekRepository;
    private final SectionRepository sectionRepository;
    private final SubtopicRepository subtopicRepository;

    private final LectureService lectureService;

    @Override
    @Transactional(readOnly = true)
    public ReadRoadmapResponseDto execute(UUID userId, Long lectureId) {

        // Lecture 조회
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // User 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 요청 유효성 검증
        if (user.getRole() == ESecurityRole.PROFESSOR) {
            if (lecture.getStatus() == EStatus.EMPTY) { // 교수이면서 강의가 EMPTY 상태인 경우

                // Lecture 정보만 기입되어있고 나머지는 null인 Dto 반환
                return ReadRoadmapResponseDto.zero(lecture, user.getName());
            }
        }

        // 학생인 경우
        else {

            // Lecture 유효성 검증
            lectureService.validateLecture(lecture);
        }

        // Subtopic List 조회
        List<Subtopic> subtopics = subtopicRepository.findAllByLectureId(lectureId);

        // Map(Section, List<Subtopic>) 생성
        Map<Section, List<Subtopic>> sectionSubtopicMap = subtopics.stream()
                .collect(Collectors.groupingBy(Subtopic::getSection));

        // Section List 조회
        List<Section> sections = sectionRepository.findAllByLectureId(lectureId);

        // Map(Week, List<Section>) 생성
        Map<Week, List<Section>> weekSectionMap = sections.stream()
                .collect(Collectors.groupingBy(Section::getWeek));

        // Week List 조회 및 Dto 변환
        List<Week> weeks = weekRepository.findAllByLectureId(lectureId);

        // WeekInfoDto List 생성
        List<ReadRoadmapResponseDto.WeekInfoDto> weekInfoDtos = weeks.stream()
                .map(week -> {
                    List<Section> weekSections = weekSectionMap.getOrDefault(week, List.of());
                    List<ReadRoadmapResponseDto.SectionInfoDto> sectionInfoDtos = weekSections.stream()
                            .map(section -> {
                                List<Subtopic> sectionSubtopics = sectionSubtopicMap.getOrDefault(section, List.of());
                                List<ReadRoadmapResponseDto.SubtopicInfoDto> subtopicInfoDtos = sectionSubtopics.stream()
                                        .map(ReadRoadmapResponseDto.SubtopicInfoDto::fromEntity)
                                        .collect(Collectors.toList());
                                return ReadRoadmapResponseDto.SectionInfoDto.of(section, subtopicInfoDtos);
                            })
                            .collect(Collectors.toList());
                    return ReadRoadmapResponseDto.WeekInfoDto.of(week, sectionInfoDtos);
                })
                .collect(Collectors.toList());

        // UserLecture 조회
        UserLecture userLecture = userLectureRepository.findByLectureId(lectureId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // Lecture 정보로 Dto 생성 및 반환
        return ReadRoadmapResponseDto.of(lecture, weekInfoDtos, userLecture.getProfessor().getName());
    }
}
