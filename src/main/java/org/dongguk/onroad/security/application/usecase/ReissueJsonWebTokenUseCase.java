package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.application.dto.response.DefaultJsonWebTokenDto;

@UseCase
public interface ReissueJsonWebTokenUseCase {

    /**
     * 1.3 Refresh Token을 이용하여 새로운 JsonWebToken을 발급하는 유스케이스
     * @param refreshToken Refresh Token
     * @return DefaultJsonWebTokenDto
     */
    DefaultJsonWebTokenDto execute(String refreshToken);
}
