package org.dongguk.onroad.security.application.service;

import org.dongguk.onroad.security.application.usecase.DeleteUserUseCase;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void execute(UUID userId) {
        userRepository.deleteById(userId);
    }
}
