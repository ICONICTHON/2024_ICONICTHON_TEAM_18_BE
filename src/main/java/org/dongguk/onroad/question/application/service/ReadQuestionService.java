package org.dongguk.onroad.question.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;
import org.dongguk.onroad.question.application.usecase.ReadQuestionUseCase;
import org.dongguk.onroad.question.domain.Question;
import org.dongguk.onroad.question.repository.QuestionRepository;
import org.dongguk.onroad.question.repository.ReplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadQuestionService implements ReadQuestionUseCase {

    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadQuestionResponseDto execute(Long lectureId, Integer weekIndex) {

        // 질문 조회
        List<Question> questions = questionRepository.findAllByLectureIdAndWeekIndex(lectureId, weekIndex);

        List<ReadQuestionResponseDto.QuestionDto> questionDto = questions.stream()
                .map(question -> {
                    Integer replyCount = replyRepository.findAllById(Collections.singleton(question.getId())).size();
                    ReadQuestionResponseDto.QuestionInfoDto infoDto = ReadQuestionResponseDto.QuestionInfoDto.fromEntity(question);
                    return ReadQuestionResponseDto.QuestionDto.of(infoDto, replyCount);
                }
        ).toList();

        return ReadQuestionResponseDto.of(questionDto);

    }
}
