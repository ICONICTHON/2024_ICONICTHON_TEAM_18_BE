package org.dongguk.onroad.question.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.question.application.dto.request.CreateQuestionRequestDto;
import org.dongguk.onroad.question.application.usecase.CreateQuestionUseCase;
import org.dongguk.onroad.question.domain.Question;
import org.dongguk.onroad.question.domain.service.QuestionService;
import org.dongguk.onroad.question.repository.QuestionRepository;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.repository.LectureRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateQuestionService implements CreateQuestionUseCase {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final LectureRepository lectureRepository;

    private final UserService userService;
    private final QuestionService questionService;

    @Override
    @Transactional
    public void execute(UUID userId, CreateQuestionRequestDto requestDto) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 학생 자격 검증
        userService.validateStudent(user);

        // 강의 조회
        Lecture lecture = lectureRepository.findById(requestDto.lectureId())
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 질문 생성
        Question question = questionService.createQuestion(
                requestDto.title(),
                requestDto.content(),
                requestDto.week(),
                lecture,
                user);

        questionRepository.save(question);
    }

}
