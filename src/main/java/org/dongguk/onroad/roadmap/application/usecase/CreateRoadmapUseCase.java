package org.dongguk.onroad.roadmap.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@UseCase
public interface CreateRoadmapUseCase {

    void execute(
            UUID userId,
            Long lectureId,
            MultipartFile file
    );
}
