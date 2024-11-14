package org.dongguk.onroad.roadmap.application.controller.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.roadmap.application.dto.request.CreateQuizRequestDto;
import org.dongguk.onroad.roadmap.application.dto.request.CreateUserChoiceRequestDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateQuizUseCase;
import org.dongguk.onroad.roadmap.application.usecase.CreateUserChoiceUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    private final CreateUserChoiceUseCase createUserChoiceUseCase;
    private final CreateQuizUseCase createQuizUseCase;

    /**
     *  3.5 퀴즈 정답 입력하기
     */
    @PostMapping("/user-choices")
    public ResponseDto<Void> createUserChoice(
            @UserID UUID userId,
            @RequestBody @Valid CreateUserChoiceRequestDto requestDto
    ) {
        createUserChoiceUseCase.execute(userId, requestDto);
        return ResponseDto.created(null);
    }

    /**
     *  3.7 강의 생성하기
     */
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

    /**
     *  3.8 로드맵 생성하기
     */
    @PostMapping(value = "/roadmaps/lectures/{lectureId}", consumes = {MediaType.APPLICATION_PDF_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
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
  
    /**
     *  3.10  생성하기
     */
    @PostMapping("/quizzes")
    public ResponseDto<Void> createQuiz(
            @UserID UUID userId,
            @RequestBody @Valid CreateQuizRequestDto requestDto
    ) {
        createQuizUseCase.execute(
                userId,
                requestDto
        );
        return ResponseDto.created(null);
    }

}
