package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.kafka.CreateRoadmapDetailsKafkaResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapDetailsUseCase;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.Week;
import org.dongguk.onroad.roadmap.domain.service.WeekService;
import org.dongguk.onroad.roadmap.repository.LectureRepository;
import org.dongguk.onroad.roadmap.repository.WeekRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoadmapDetailsService implements CreateRoadmapDetailsUseCase {

    private final LectureRepository lectureRepository;
    private final WeekRepository weekRepository;

    private final WeekService weekService;

    @Override
    public void execute(CreateRoadmapDetailsKafkaResponseDto responseDto) {

        Lecture lecture = lectureRepository.findById(responseDto.lectureId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        Week week = createWeek(
                lecture,
                responseDto.title(),
                responseDto.overallSummary()
        );



    }

    private Week createWeek(Lecture lecture, String title, String overallSummary) {
        int weekIndex = weekRepository.countByLecture(lecture) + 1;

        return weekService.createWeek(
                title,
                overallSummary,
                weekIndex,
                lecture
        );
    }


}
