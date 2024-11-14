package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadQuizSummaryResponseDto;

@UseCase
public interface ReadQuizSummaryUseCase {

    /**
     * 3.4 퀴즈 요약 정보를 읽어오는 유스케이스
     * @param subtopicId 서브토픽 ID
     * @return ReadQuizSummaryResponseDto
     */
    ReadQuizSummaryResponseDto execute(Long subtopicId);
}
