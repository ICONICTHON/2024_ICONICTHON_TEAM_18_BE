package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.response.ReadCheckPointSummaryResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadCheckPointSummaryUseCase;
import org.dongguk.onroad.roadmap.domain.CheckPoint;
import org.dongguk.onroad.roadmap.domain.Subtopic;
import org.dongguk.onroad.roadmap.repository.CheckPointRepository;
import org.dongguk.onroad.roadmap.repository.SubtopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadCheckPointSummaryService implements ReadCheckPointSummaryUseCase {

    private final CheckPointRepository checkPointRepository;
    private final SubtopicRepository subtopicRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadCheckPointSummaryResponseDto execute(Long subtopicId) {

        // 서브토픽에 해당하는 체크포인트 리스트 조회
        List<CheckPoint> checkPointList = checkPointRepository.findBySubtopicId(subtopicId);

        // 서브토픽 조회
        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return ReadCheckPointSummaryResponseDto.of(subtopic.getTitle(), subtopic.getDetail(), checkPointList);

    }
}
