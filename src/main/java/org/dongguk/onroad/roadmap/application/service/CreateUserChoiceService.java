package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.request.CreateUserChoiceRequestDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateUserChoiceUseCase;
import org.dongguk.onroad.roadmap.domain.Choice;
import org.dongguk.onroad.roadmap.domain.UserChoice;
import org.dongguk.onroad.roadmap.domain.service.UserChoiceService;
import org.dongguk.onroad.roadmap.repository.ChoiceRepository;
import org.dongguk.onroad.roadmap.repository.UserChoiceRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateUserChoiceService implements CreateUserChoiceUseCase {

    private final UserChoiceRepository userChoiceRepository;
    private final UserRepository userRepository;
    private final ChoiceRepository choiceRepository;

    private final UserService userService;
    private final UserChoiceService userChoiceService;


    @Override
    @Transactional
    public void execute(UUID userId, CreateUserChoiceRequestDto requestDto) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 학생 유효성 검사
        userService.validateStudent(user);

        requestDto.userQuizList().forEach(userQuiz -> {
            // 선지 조회
            Choice choice = choiceRepository.findById(userQuiz.choiceId())
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

            // 유저 선택지 생성
            UserChoice userChoice = userChoiceService.createUserChoice(user, choice);
            userChoiceRepository.save(userChoice);
        });

    }
}
