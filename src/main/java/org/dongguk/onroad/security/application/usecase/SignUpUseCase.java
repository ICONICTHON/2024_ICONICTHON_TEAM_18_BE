package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.application.dto.request.SignUpRequestDto;

@UseCase
public interface SignUpUseCase {

    /**
     * 2.1 회원가입을 처리하는 유스케이스
     * @param requestDto SignUpRequestDto
     */
    void execute(SignUpRequestDto requestDto);
}
