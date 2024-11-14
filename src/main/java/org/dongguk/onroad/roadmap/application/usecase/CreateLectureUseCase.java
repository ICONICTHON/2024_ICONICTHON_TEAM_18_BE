package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.roadmap.application.dto.request.CreateLectureRequestDto;

import java.util.UUID;

@UseCase
public interface CreateLectureUseCase {
    /**
     * 3.7 강의를 생성하는 유스케이스
     * @param userId 사용자 ID
     * @param requestDto CreateLectureRequestDto
     */
    void execute(
            UUID userId,
            CreateLectureRequestDto requestDto
    );
}
