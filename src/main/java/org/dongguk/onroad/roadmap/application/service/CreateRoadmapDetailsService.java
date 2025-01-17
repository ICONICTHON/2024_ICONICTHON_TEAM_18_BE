package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.core.utility.S3Util;
import org.dongguk.onroad.roadmap.application.dto.kafka.CreateRoadmapDetailsKafkaResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapDetailsUseCase;
import org.dongguk.onroad.roadmap.domain.*;
import org.dongguk.onroad.roadmap.domain.service.LectureService;
import org.dongguk.onroad.roadmap.domain.service.SectionService;
import org.dongguk.onroad.roadmap.domain.service.SubtopicService;
import org.dongguk.onroad.roadmap.domain.service.WeekService;
import org.dongguk.onroad.roadmap.domain.type.EStatus;
import org.dongguk.onroad.roadmap.repository.LectureRepository;
import org.dongguk.onroad.roadmap.repository.WeekRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateRoadmapDetailsService implements CreateRoadmapDetailsUseCase {

    private final LectureRepository lectureRepository;
    private final WeekRepository weekRepository;

    private final WeekService weekService;
    private final SectionService sectionService;
    private final SubtopicService subtopicService;
    private final LectureService lectureService;
    private final S3Util s3Util;

    @Override
    @Transactional
    public void execute(CreateRoadmapDetailsKafkaResponseDto responseDto) {

        // 강의 조회
        Lecture lecture = lectureRepository.findById(responseDto.lectureId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // Week 생성
        Week week = createWeek(
                lecture,
                responseDto.title(),
                responseDto.overallSummary()
        );

        // Section 및 Subtopic 생성
        responseDto.sections().forEach(sectionDto -> {
            Section section = createSection(
                    week,
                    sectionDto.title(),
                    sectionDto.description()
            );

            // Subtopic 생성
            sectionDto.subtopics().forEach(subSectionDto -> {
                Subtopic subtopic = createSubtopic(
                        section,
                        subSectionDto.title(),
                        subSectionDto.detail()
                );

                // Checkpoint 생성
                subSectionDto.checkpoints().forEach(
                        checkpointContent -> createCheckPoint(subtopic, checkpointContent)
                );
            });
        });

        // 강의 상태 Completed로 변경
        lecture = lectureService.updateStatusCompleted(lecture);
        lectureRepository.save(lecture);

        // PDF 파일 S3에서 삭제
        s3Util.deleteFile(responseDto.fileUrl());
    }

    private Week createWeek(Lecture lecture, String title, String overallSummary) {
        int weekIndex = weekRepository.countByLecture(lecture) + 1;
        Week week = weekService.createWeek(title, overallSummary, weekIndex, lecture);
        if(weekIndex == 1){
            week.updateIsSelected(true);
        }
        lecture.getWeeks().add(week);

        return week;
    }

    private Section createSection(Week week, String title, String content) {
        Section section = sectionService.createSection(title, content, week);
        week.getSections().add(section);

        return section;
    }

    private Subtopic createSubtopic(Section section, String title, String detail) {
        Subtopic subtopic = subtopicService.createSubtopic(title, detail, section);
        section.getSubtopics().add(subtopic);

        return subtopic;
    }

    private void createCheckPoint(Subtopic subtopic, String content) {
        CheckPoint checkPoint = CheckPoint.builder()
                .content(content)
                .subtopic(subtopic)
                .build();
        subtopic.getCheckPoints().add(checkPoint);
    }
}
