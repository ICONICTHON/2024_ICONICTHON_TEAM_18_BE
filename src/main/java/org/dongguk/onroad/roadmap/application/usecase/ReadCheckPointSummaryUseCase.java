package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.response.ReadCheckPointSummaryResponseDto;

@UseCase
public interface ReadCheckPointSummaryUseCase {

    ReadCheckPointSummaryResponseDto execute(Long subtopicId);
}
