package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.application.dto.response.DefaultJsonWebTokenDto;
import org.dongguk.onroad.security.info.CustomUserPrincipal;

@UseCase
public interface LoginUseCase {

    /**
     * 1.1 Security에서 사용되는 Login 유스케이스
     * @param principal UserPrincipal
     * @param jsonWebTokenDto DefaultJsonWebTokenDto
     */
    void execute(CustomUserPrincipal principal, DefaultJsonWebTokenDto jsonWebTokenDto);
}
