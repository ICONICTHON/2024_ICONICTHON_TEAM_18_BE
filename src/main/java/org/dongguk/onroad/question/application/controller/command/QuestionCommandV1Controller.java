package org.dongguk.onroad.question.application.controller.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.question.application.dto.request.CreateQuestionRequestDto;
import org.dongguk.onroad.question.application.usecase.CreateQuestionUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuestionCommandV1Controller {
    private final CreateQuestionUseCase createQuestionUseCase;

    @PostMapping("/v1/questions")
    public ResponseDto<Void> createQuestion(
            @UserID UUID userId,
            @RequestBody @Valid CreateQuestionRequestDto requestDto
    ) {
        createQuestionUseCase.execute(userId, requestDto);
        return ResponseDto.created(null);
    }
}
