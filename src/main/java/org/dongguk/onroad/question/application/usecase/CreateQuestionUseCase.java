package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.request.CreateQuestionRequestDto;

import java.util.UUID;

@UseCase
public interface CreateQuestionUseCase {

    void execute(UUID userId, CreateQuestionRequestDto requestDto);

}
