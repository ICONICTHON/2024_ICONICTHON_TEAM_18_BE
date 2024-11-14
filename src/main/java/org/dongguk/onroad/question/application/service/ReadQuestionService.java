package org.dongguk.onroad.question.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;
import org.dongguk.onroad.question.application.usecase.ReadQuestionUseCase;
import org.dongguk.onroad.question.domain.Question;
import org.dongguk.onroad.question.repository.QuestionRepository;
import org.dongguk.onroad.question.repository.ReplyRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadQuestionService implements ReadQuestionUseCase {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadQuestionResponseDto execute(UUID userId, Long lectureId, Integer weekIndex) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 학생이라면, 모든 질문의 질문자 명이 anonymous로 변경
        if (user.getRole() == ESecurityRole.STUDENT) {
            // 질문 조회
            List<Question> questions = questionRepository.findAllByLectureIdAndWeekIndex(lectureId, weekIndex);

            List<ReadQuestionResponseDto.QuestionDto> questionDto = questions.stream()
                    .map(question -> {
                                Integer replyCount = replyRepository.findAllByQuestionId(question.getId()).size();
                                ReadQuestionResponseDto.QuestionInfoDto infoDto = ReadQuestionResponseDto.QuestionInfoDto.fromEntityStudent(question);
                                return ReadQuestionResponseDto.QuestionDto.of(infoDto, replyCount);
                            }
                    ).toList();

            return ReadQuestionResponseDto.of(questionDto);
        }

        // 질문 조회
        List<Question> questions = questionRepository.findAllByLectureIdAndWeekIndex(lectureId, weekIndex);

        List<ReadQuestionResponseDto.QuestionDto> questionDto = questions.stream()
                .map(question -> {
                    Integer replyCount = replyRepository.findAllByQuestionId(question.getId()).size();
                    ReadQuestionResponseDto.QuestionInfoDto infoDto = ReadQuestionResponseDto.QuestionInfoDto.fromEntityProfessor(question);
                    return ReadQuestionResponseDto.QuestionDto.of(infoDto, replyCount);
                }
        ).toList();

        return ReadQuestionResponseDto.of(questionDto);

    }
}
