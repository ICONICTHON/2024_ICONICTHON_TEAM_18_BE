package org.dongguk.onroad.roadmap.application.controller;

import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadLectureOverviewUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RoadmapQueryV1Controller {

    private final ReadLectureOverviewUseCase readLectureOverviewUseCase;

    @GetMapping("/lectures/overviews")
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



}
