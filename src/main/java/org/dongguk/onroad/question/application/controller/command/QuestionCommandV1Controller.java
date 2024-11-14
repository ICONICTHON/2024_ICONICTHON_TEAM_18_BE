package org.dongguk.onroad.question.application.controller.command;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.question.application.dto.request.CreateQuestionRequestDto;
import org.dongguk.onroad.question.application.dto.request.CreateReplyRequestDto;
import org.dongguk.onroad.question.application.usecase.CreateQuestionUseCase;
import org.dongguk.onroad.question.application.usecase.CreateReplyUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuestionCommandV1Controller {
    private final CreateQuestionUseCase createQuestionUseCase;
    private final CreateReplyUseCase createReplyUseCase;

    @PostMapping("/v1/questions")
    public ResponseDto<Void> createQuestion(
            @UserID UUID userId,
            @RequestBody @Valid CreateQuestionRequestDto requestDto
    ) {
        createQuestionUseCase.execute(userId, requestDto);
        return ResponseDto.created(null);
    }

    @PostMapping("/v1/questions/{id}/replies")
    public ResponseDto<Void> createReply(
            @UserID UUID userId,
            @PathVariable Long id,
            @RequestBody @Valid CreateReplyRequestDto requestDto
    ) {
        createReplyUseCase.execute(userId, id, requestDto);
        return ResponseDto.created(null);
    }
}
