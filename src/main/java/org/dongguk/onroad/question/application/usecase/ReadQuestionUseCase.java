package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;

import java.util.UUID;

@UseCase
public interface ReadQuestionUseCase {
    ReadQuestionResponseDto execute(UUID userId, Long lectureId, Integer weekIndex);
}
