package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.application.dto.request.SignUpRequestDto;

@UseCase
public interface SignUpUseCase {
    void execute(SignUpRequestDto requestDto);
}
