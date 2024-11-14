package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadQuizSummaryResponseDto;

@UseCase
public interface ReadQuizSummaryUseCase {
    ReadQuizSummaryResponseDto execute(Long subtopicId);
}
