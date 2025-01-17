package org.dongguk.onroad.question.application.controller.query;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;
import org.dongguk.onroad.question.application.dto.response.ReadReplyResponseDto;
import org.dongguk.onroad.question.application.usecase.ReadQuestionUseCase;
import org.dongguk.onroad.question.application.usecase.ReadReplyUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuestionQueryV1Controller {

    private final ReadQuestionUseCase readQuestionUseCase;
    private final ReadReplyUseCase readReplyUseCase;

    @GetMapping("/v1/questions")
    public ResponseDto<ReadQuestionResponseDto> readQuestions(
            @UserID UUID userId,
            @RequestParam("id") Long lectureId,
            @RequestParam("week") Integer weekIndex
    ) {
        return ResponseDto.ok(readQuestionUseCase.execute(userId, lectureId, weekIndex));
    }

    @GetMapping("/v1/questions/{id}/replies")
    public ResponseDto<ReadReplyResponseDto> readReplies(
            @UserID UUID userId,
            @PathVariable Long id
    ) {
        return ResponseDto.ok(readReplyUseCase.execute(userId, id));
    }
}
