package org.dongguk.onroad.security.application.service;

import org.dongguk.onroad.security.application.usecase.LogoutUseCase;
import org.dongguk.onroad.security.info.CustomUserPrincipal;
import org.dongguk.onroad.security.repository.redis.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void execute(CustomUserPrincipal principal) {
        UUID userId = principal.getId();

        refreshTokenRepository.deleteById(userId);
    }
}
