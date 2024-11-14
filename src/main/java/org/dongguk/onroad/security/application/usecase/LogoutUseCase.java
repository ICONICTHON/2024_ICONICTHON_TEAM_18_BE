package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.info.CustomUserPrincipal;

@UseCase
public interface LogoutUseCase {

    /**
     * 1.2 Security 단에서 사용되는 Logout 유스케이스
     * @param principal UserPrincipal
     */
    void execute(CustomUserPrincipal principal);
}

