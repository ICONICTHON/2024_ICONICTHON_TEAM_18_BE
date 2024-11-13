package org.dongguk.onroad.security.application.service;

import org.dongguk.onroad.security.application.dto.response.DefaultJsonWebTokenDto;
import org.dongguk.onroad.security.application.usecase.LoginUseCase;
import org.dongguk.onroad.security.domain.service.RefreshTokenService;
import org.dongguk.onroad.security.info.CustomUserPrincipal;
import org.dongguk.onroad.security.repository.redis.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void execute(CustomUserPrincipal principal, DefaultJsonWebTokenDto jsonWebTokenDto) {

        UUID userId = principal.getId();
        String refreshToken = jsonWebTokenDto.getRefreshToken();

        // Refresh Token 저장
        if (refreshToken != null) {
            refreshTokenRepository.save(refreshTokenService.createRefreshToken(userId, refreshToken));
        }
    }
}
