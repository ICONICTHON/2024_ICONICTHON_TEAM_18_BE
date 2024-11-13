package org.dongguk.onroad.security.handler.login;

import org.dongguk.onroad.core.utility.HttpServletUtil;
import org.dongguk.onroad.core.utility.JsonWebTokenUtil;
import org.dongguk.onroad.security.application.dto.response.DefaultJsonWebTokenDto;
import org.dongguk.onroad.security.application.usecase.LoginUseCase;
import org.dongguk.onroad.security.info.CustomUserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DefaultLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginUseCase loginUseCase;

    private final JsonWebTokenUtil jwtUtil;
    private final HttpServletUtil httpServletUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {
        CustomUserPrincipal principal = (CustomUserPrincipal) authentication.getPrincipal();

        DefaultJsonWebTokenDto jsonWebTokenDto = jwtUtil.generateDefaultJsonWebTokens(
                principal.getId(),
                principal.getRole()
        );

        loginUseCase.execute(principal, jsonWebTokenDto);

        httpServletUtil.onSuccessBodyResponseWithJWTBody(response, jsonWebTokenDto);
    }
}
