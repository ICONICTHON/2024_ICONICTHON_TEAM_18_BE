package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.response.ReadReplyResponseDto;

@UseCase
public interface ReadReplyUseCase {

    ReadReplyResponseDto execute(Long questionId);
}
