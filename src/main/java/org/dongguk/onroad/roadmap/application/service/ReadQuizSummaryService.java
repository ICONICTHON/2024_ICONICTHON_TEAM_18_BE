package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.roadmap.application.dto.response.ReadQuizSummaryResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadQuizSummaryUseCase;
import org.dongguk.onroad.roadmap.domain.Choice;
import org.dongguk.onroad.roadmap.domain.Quiz;
import org.dongguk.onroad.roadmap.repository.ChoiceRepository;
import org.dongguk.onroad.roadmap.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadQuizSummaryService implements ReadQuizSummaryUseCase {

    private final QuizRepository quizRepository;
    private final ChoiceRepository choiceRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadQuizSummaryResponseDto execute(Long subtopicId) {

        // Choice List 조회
        List<Choice> choices = choiceRepository.findAllBySubtopicId(subtopicId);

        // Map(Quiz, List<Choice>) 생성
        Map<Quiz, List<Choice>> quizChoiceMap = choices.stream()
                .collect(Collectors.groupingBy(Choice::getQuiz));

        // Quiz List 조회
        List<Quiz> quizzes = quizRepository.findAllBySubtopicId(subtopicId);

        // ReadQuizSummaryResponseDto 반환
        return ReadQuizSummaryResponseDto.of(quizzes.stream()
                .map(quiz -> {
                    List<Choice> quizChoices = quizChoiceMap.getOrDefault(quiz, List.of());
                    List<ReadQuizSummaryResponseDto.ChoiceDto> choiceDtos = quizChoices.stream()
                            .map(ReadQuizSummaryResponseDto.ChoiceDto::fromEntity)
                            .toList();
                    return ReadQuizSummaryResponseDto.QuizDto.of(quiz.getId(), quiz.getContent(), choiceDtos);
                })
                .toList());
    }

}
