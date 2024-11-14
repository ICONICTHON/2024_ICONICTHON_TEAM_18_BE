package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;

@UseCase
public interface ReadQuestionUseCase {
    ReadQuestionResponseDto execute(Long lectureId, Integer weekIndex);
}
