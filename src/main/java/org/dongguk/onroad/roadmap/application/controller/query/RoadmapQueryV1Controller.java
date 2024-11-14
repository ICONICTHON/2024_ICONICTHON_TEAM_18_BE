package org.dongguk.onroad.roadmap.application.controller.query;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLectureBriefResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadLectureBriefUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoadmapQueryV1Controller {

    private final ReadLectureBriefUseCase readLectureBriefUseCase;

    @GetMapping("/v1/lectures/briefs")
    public ResponseDto<ReadLectureBriefResponseDto> readLectureBrief(
            @UserID UUID userId
            ) {
        return ResponseDto.ok(readLectureBriefUseCase.execute(userId));
    }
}
