package org.dongguk.onroad.security.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.security.application.dto.response.ReadUserBriefResponseDto;
import org.dongguk.onroad.security.application.usecase.ReadUserBriefUseCase;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadUserBriefService implements ReadUserBriefUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadUserBriefResponseDto execute(UUID userId) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return ReadUserBriefResponseDto.fromEntity(user);
    }

}
