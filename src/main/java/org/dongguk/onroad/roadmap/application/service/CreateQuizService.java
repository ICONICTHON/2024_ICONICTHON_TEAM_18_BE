package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.request.CreateQuizRequestDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateQuizUseCase;
import org.dongguk.onroad.roadmap.domain.CheckPoint;
import org.dongguk.onroad.roadmap.domain.Choice;
import org.dongguk.onroad.roadmap.domain.Quiz;
import org.dongguk.onroad.roadmap.domain.service.ChoiceService;
import org.dongguk.onroad.roadmap.domain.service.QuizService;
import org.dongguk.onroad.roadmap.domain.type.EQuizType;
import org.dongguk.onroad.roadmap.repository.CheckPointRepository;
import org.dongguk.onroad.roadmap.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CreateQuizService implements CreateQuizUseCase {

    private final CheckPointRepository checkPointRepository;

    private final QuizService quizService;
    private final ChoiceService choiceService;
    private final QuizRepository quizRepository;

    @Override
    @Transactional
    public void execute(UUID userId, CreateQuizRequestDto requestDto) {

        List<Quiz> quizList = requestDto.quizList().stream()
                .map(quizRequestDto -> {
                    CheckPoint checkPoint = checkPointRepository.findById(quizRequestDto.id())
                            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

                    Quiz quiz = quizService.createQuiz(
                            EQuizType.MULTIPLE_CHOICE,
                            quizRequestDto.content(),
                            checkPoint
                    );

                    quizRequestDto.choiceList().forEach(
                            choiceRequestDto -> choiceService.createChoice(
                                    choiceRequestDto.content(),
                                    choiceRequestDto.isAnswer(),
                                    quiz
                            ));

                    return quiz;
                }).toList();

        quizRepository.saveAll(quizList);
    }
}
