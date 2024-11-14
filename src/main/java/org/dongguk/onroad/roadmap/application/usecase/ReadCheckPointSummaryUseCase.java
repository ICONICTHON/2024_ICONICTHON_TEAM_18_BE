package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadCheckPointSummaryResponseDto;

@UseCase
public interface ReadCheckPointSummaryUseCase {

    /**
     * 3.3 체크포인트 요약 정보를 읽어오는 유스케이스
     * @param subtopicId 서브토픽 ID
     * @return ReadCheckPointSummaryResponseDto
     */
    ReadCheckPointSummaryResponseDto execute(Long subtopicId);
}
