package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.event.PdfEvent;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapUseCase;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateRoadmapService implements CreateRoadmapUseCase {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserRepository userRepository;

    @Override
    public void execute(UUID userId, Long lectureId, MultipartFile file) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        if(!user.getRole().equals(ESecurityRole.PROFESSOR)){
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }

        applicationEventPublisher.publishEvent(
                PdfEvent.builder()
                        .lectureId(lectureId)
                        .file(file)
                        .build()
        );
    }


}
