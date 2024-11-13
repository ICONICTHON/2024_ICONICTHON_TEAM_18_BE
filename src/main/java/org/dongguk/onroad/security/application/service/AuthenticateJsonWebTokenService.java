package org.dongguk.onroad.security.application.service;

import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.security.application.usecase.AuthenticateJsonWebTokenUseCase;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.info.CustomUserPrincipal;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticateJsonWebTokenService implements AuthenticateJsonWebTokenUseCase {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public CustomUserPrincipal execute(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return userService.createCustomUserPrincipalByUser(user);
    }
}
