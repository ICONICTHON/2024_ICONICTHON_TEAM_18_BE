package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@UseCase
public interface CreateRoadmapUseCase {
    /**
     * 3.8 Roadmap을 생성하는 유스케이스
     * @param userId 사용자 ID
     * @param lectureId 강의 ID
     * @param file 파일
     */
    void execute(
            UUID userId,
            Long lectureId,
            MultipartFile file
    );
}
