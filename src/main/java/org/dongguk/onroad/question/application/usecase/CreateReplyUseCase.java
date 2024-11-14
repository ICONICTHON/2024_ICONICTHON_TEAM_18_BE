package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.request.CreateReplyRequestDto;

import java.util.UUID;

@UseCase
public interface CreateReplyUseCase {

    void execute(UUID userId, Long questionId, CreateReplyRequestDto requestDto);
}