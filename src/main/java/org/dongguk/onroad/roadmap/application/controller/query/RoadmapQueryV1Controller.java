package org.dongguk.onroad.roadmap.application.controller.query;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.roadmap.application.dto.response.ReadCheckPointSummaryResponseDto;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLectureBriefResponseDto;
import org.dongguk.onroad.roadmap.application.dto.response.ReadQuizSummaryResponseDto;
import org.dongguk.onroad.roadmap.application.dto.response.ReadRoadmapResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoadmapQueryV1Controller {

    private final ReadLectureBriefUseCase readLectureBriefUseCase;
    private final ReadLectureOverviewUseCase readLectureOverviewUseCase;
    private final ReadRoadmapUseCase readRoadmapUseCase;
    private final ReadCheckPointSummaryUseCase readCheckPointSummaryUseCase;
    private final ReadQuizSummaryUseCase readQuizSummaryUseCase;

    @GetMapping("/v1/lectures/briefs")
    public ResponseDto<ReadLectureBriefResponseDto> readLectureBrief(
            @UserID UUID userId
            ) {
        return ResponseDto.ok(readLectureBriefUseCase.execute(userId));
    }

    @GetMapping("/v1/lectures/overviews")
    public ResponseDto<?> readLecturesOverviews(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @UserID UUID userId
    ){
        return ResponseDto.ok(
                readLectureOverviewUseCase.execute(
                        page,
                        size,
                        userId
                )
        );
    }

    @GetMapping("/v1/lectures/{id}/roadmaps")
    public ResponseDto<ReadRoadmapResponseDto> readRoadmap(
            @UserID UUID userId,
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readRoadmapUseCase.execute(userId, id));
    }

    @GetMapping("/v1/subtopics/{id}/check-points/summaries")
    public ResponseDto<ReadCheckPointSummaryResponseDto> readCheckPointSummary(
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readCheckPointSummaryUseCase.execute(id));
    }

    @GetMapping("/v1/subtopics/{id}/check-points/quizzes/choices/summaries")
    public ResponseDto<ReadQuizSummaryResponseDto> readQuizSummary(
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readQuizSummaryUseCase.execute(id));
    }
}
