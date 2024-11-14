package org.dongguk.onroad.question.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.question.application.dto.response.ReadQuestionResponseDto;

import java.util.UUID;

@UseCase
public interface ReadQuestionUseCase {

    /**
     * 질문을 읽는 유스케이스
     * @param userId 사용자 ID
     * @param lectureId 강의 ID
     * @param weekIndex 주차
     * @return ReadQuestionResponseDto 읽어온 질문
     */
    ReadQuestionResponseDto execute(UUID userId, Long lectureId, Integer weekIndex);
}
