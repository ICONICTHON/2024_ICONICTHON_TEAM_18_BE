package org.dongguk.onroad.security.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.security.application.dto.request.SignUpRequestDto;
import org.dongguk.onroad.security.application.usecase.SignUpUseCase;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;

    @Override
    @Transactional
    public void execute(SignUpRequestDto requestDto) {

        // 이미 가입된 아이디인지 확인
        userRepository.findBySerialId(requestDto.serialId())
                .ifPresent(user -> {
                    throw new CommonException(ErrorCode.ALREADY_EXIST_ID);
                });

        // 유저 생성
        User user = userService.createUser(
                requestDto.serialId(),
                bCryptPasswordEncoder.encode(requestDto.password()),
                requestDto.name(),
                requestDto.role()
        );
        userRepository.save(user);

    }
}
