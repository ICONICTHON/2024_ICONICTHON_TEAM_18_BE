package org.dongguk.onroad.security.application.controller;

import org.dongguk.onroad.core.annotation.security.UserID;
import org.dongguk.onroad.core.constant.Constants;
import org.dongguk.onroad.core.dto.ResponseDto;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.core.utility.HeaderUtil;
import org.dongguk.onroad.security.application.dto.request.SignUpRequestDto;
import org.dongguk.onroad.security.application.dto.response.DefaultJsonWebTokenDto;
import org.dongguk.onroad.security.application.usecase.DeleteUserUseCase;
import org.dongguk.onroad.security.application.usecase.ReissueJsonWebTokenUseCase;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.security.application.usecase.SignUpUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@Hidden
public class AuthController {
    private final SignUpUseCase signUpUseCase;
    private final ReissueJsonWebTokenUseCase reissueJsonWebTokenUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    /**
     * 1.3 JWT 재발급
     */
    @PostMapping("/api/v1/auth/reissue/token")
    public ResponseDto<DefaultJsonWebTokenDto> reissueDefaultJsonWebToken(
            HttpServletRequest request
    ) {
        String refreshToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_HEADER_ERROR));

        return ResponseDto.created(reissueJsonWebTokenUseCase.execute(refreshToken));
    }

    /**
     * 2.1 일반 회원가입
     */
    @PostMapping("/api/v1/auth/sign-up")
    public ResponseDto<Void> signUp(
            @RequestBody @Valid SignUpRequestDto requestDto
            ) {
        signUpUseCase.execute(requestDto);
        return ResponseDto.created(null);
    }

    /**
     * 2.2 회원 탈퇴
     */
    @DeleteMapping("/api/v1/auth")
    public ResponseDto<?> deleteAccount(
            @UserID UUID userId
    ) {
        deleteUserUseCase.execute(userId);
        return ResponseDto.ok(null);
    }
}
