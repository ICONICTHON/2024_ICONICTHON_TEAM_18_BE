package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLastUpdatedRoadmapResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadLastUpdatedRoadmapUseCase;
import org.dongguk.onroad.roadmap.domain.*;
import org.dongguk.onroad.roadmap.repository.*;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadLastUpdatedRoadMapService implements ReadLastUpdatedRoadmapUseCase {

    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;
    private final WeekRepository weekRepository;
    private final SubtopicRepository subtopicRepository;
    private final SectionRepository sectionRepository;

    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public ReadLastUpdatedRoadmapResponseDto execute(UUID userId) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 학생 자격 검증
        userService.validateStudent(user);

        // UserLecture 조회
        List<UserLecture> userLectures = userLectureRepository.findByStudentOrProfessor(user);

        // Lecture 조회
        List<Lecture> lectures = userLectures.stream()
                .map(UserLecture::getLecture)
                .toList();

        // 모든 lectures가 가지고 있는 Weeks들에 대해 createdAt이 가장 최근인 단 하나의 Week 조회
        Week lastUpdatedWeek = lectures.stream()
                .flatMap(lecture -> weekRepository.findByLectureOrderByCreatedAtDesc(lecture).stream())
                .findFirst()
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        Lecture lecture = lastUpdatedWeek.getLecture();

        // Subtopic List 조회
        List<Subtopic> subtopics = subtopicRepository.findAllByLectureId(lecture.getId());

        // Map(Section, List<Subtopic>) 생성
        Map<Section, List<Subtopic>> sectionSubtopicMap = subtopics.stream()
                .collect(Collectors.groupingBy(Subtopic::getSection));

        // Section List 조회
        List<Section> sections = sectionRepository.findAllByLectureId(lecture.getId());

        // Map(Week, List<Section>) 생성
        Map<Week, List<Section>> weekSectionMap = sections.stream()
                .collect(Collectors.groupingBy(Section::getWeek));

        // Week List 조회 및 Dto 변환
        List<Week> weeks = weekRepository.findAllByLectureId(lecture.getId());

        List<ReadLastUpdatedRoadmapResponseDto.WeekInfoDto> weekInfoDtos = weeks.stream()
                .map(week -> {
                    List<Section> weekSections = weekSectionMap.getOrDefault(week, List.of());
                    List<ReadLastUpdatedRoadmapResponseDto.SectionInfoDto> sectionInfoDtos = weekSections.stream()
                            .map(section -> {
                                List<Subtopic> sectionSubtopics = sectionSubtopicMap.getOrDefault(section, List.of());
                                List<ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto> subtopicInfoDtos = sectionSubtopics.stream()
                                        .map(ReadLastUpdatedRoadmapResponseDto.SubtopicInfoDto::fromEntity)
                                        .collect(Collectors.toList());
                                return ReadLastUpdatedRoadmapResponseDto.SectionInfoDto.of(section, subtopicInfoDtos);
                            })
                            .collect(Collectors.toList());
                    return ReadLastUpdatedRoadmapResponseDto.WeekInfoDto.of(week, sectionInfoDtos);
                })
                .toList();

        // UserLecture 조회
        UserLecture userLecture = userLectureRepository.findByLectureId(lecture.getId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return ReadLastUpdatedRoadmapResponseDto.of(lecture, weekInfoDtos, user.getName());
    }
}
