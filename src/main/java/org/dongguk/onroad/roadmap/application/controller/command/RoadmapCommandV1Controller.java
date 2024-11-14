package org.dongguk.onroad.roadmap.application.controller.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.roadmap.application.dto.request.CreateLectureRequestDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateLectureUseCase;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class RoadmapCommandV1Controller {


    private final CreateLectureUseCase createLectureUseCase;
    private final CreateRoadmapUseCase createRoadmapUseCase;

    @PostMapping("/lectures")
    public ResponseDto<?> createLecture(
            @UserID UUID userId,
            @Valid @RequestBody CreateLectureRequestDto requestDto
    ) {

        createLectureUseCase.execute(
                userId,
                requestDto
        );

        return ResponseDto.created(null);
    }

    @PostMapping(value = "/roadmaps/lectures/{lectureId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<?> createRoadmap(
            @UserID UUID userId,
            @PathVariable Long lectureId,
            @RequestParam("file") MultipartFile file
    ) {
        createRoadmapUseCase.execute(
                userId,
                lectureId,
                file
        );
        return ResponseDto.ok(null);
    }
}
