package org.dongguk.onroad.question.application.controller.query;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;
import org.dongguk.onroad.question.application.usecase.ReadQuestionUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionQueryV1Controller {

    private final ReadQuestionUseCase readQuestionUseCase;

    @GetMapping("/v1/questions")
    public ResponseDto<ReadQuestionResponseDto> readQuestions(
            @RequestParam("id") Long lectureId,
            @RequestParam("week") Integer weekIndex
    ) {
        return ResponseDto.ok(readQuestionUseCase.execute(lectureId, weekIndex));
    }
}
