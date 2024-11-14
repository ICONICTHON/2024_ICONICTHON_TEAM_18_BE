package org.dongguk.onroad.roadmap.application.controller.query;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.roadmap.application.dto.response.*;
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
    private final ReadLastUpdatedRoadmapUseCase readLastUpdatedRoadmapUseCase;

    /**
     *  3.1 강의 이름 리스트 조회하기
     */
    @GetMapping("/v1/lectures/briefs")
    public ResponseDto<ReadLectureBriefResponseDto> readLectureBrief(
            @UserID UUID userId
            ) {
        return ResponseDto.ok(readLectureBriefUseCase.execute(userId));
    }

    /**
     *  3.2 로드맵 조회하기
     */
    @GetMapping("/v1/lectures/{id}/roadmaps")
    public ResponseDto<ReadRoadmapResponseDto> readRoadmap(
            @UserID UUID userId,
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readRoadmapUseCase.execute(userId, id));
    }

    /**
     *  3.3 체크포인트 조회하기
     */
    @GetMapping("/v1/subtopics/{id}/check-points/summaries")
    public ResponseDto<ReadCheckPointSummaryResponseDto> readCheckPointSummary(
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readCheckPointSummaryUseCase.execute(id));
    }

    /**
     *  3.4 퀴즈 리스트 조회하기
     */
    @GetMapping("/v1/subtopics/{id}/check-points/quizzes/choices/summaries")
    public ResponseDto<ReadQuizSummaryResponseDto> readQuizSummary(
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readQuizSummaryUseCase.execute(id));
    }

    /**
     *  3.6 내 강의 조회하기
     */
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

    /**
     *  3.9 최근 업데이트된 로드맵 조회하기
     */
    @GetMapping("/v1/roadmaps/created-at")
    public ResponseDto<ReadLastUpdatedRoadmapResponseDto> readLastUpdatedRoadmap(
            @UserID UUID userId
    ) {
        return ResponseDto.ok(readLastUpdatedRoadmapUseCase.execute(userId));
    }
}
