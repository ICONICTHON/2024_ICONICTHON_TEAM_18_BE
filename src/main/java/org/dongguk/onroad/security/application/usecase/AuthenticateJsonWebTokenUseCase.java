package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.info.CustomUserPrincipal;

import java.util.UUID;

@UseCase
public interface AuthenticateJsonWebTokenUseCase {

    /**
     * Security에서 사용되는, CustomUserPrincipal을 생성하는 유스케이스
     * @param userId 유저 ID
     * @return CustomUserPrincipal
     */
    CustomUserPrincipal execute(UUID userId);
}
