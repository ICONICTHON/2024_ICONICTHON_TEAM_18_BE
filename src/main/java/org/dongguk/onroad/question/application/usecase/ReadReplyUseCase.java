package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.response.ReadReplyResponseDto;

import java.util.UUID;

@UseCase
public interface ReadReplyUseCase {

    ReadReplyResponseDto execute(UUID userId, Long questionId);
}
