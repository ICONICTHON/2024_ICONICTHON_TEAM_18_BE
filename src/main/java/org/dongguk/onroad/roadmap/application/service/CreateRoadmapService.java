package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.core.utility.S3Util;
import org.dongguk.onroad.roadmap.application.event.PdfEvent;
import org.dongguk.onroad.roadmap.application.usecase.CreateRoadmapUseCase;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.service.LectureService;
import org.dongguk.onroad.roadmap.domain.type.EStatus;
import org.dongguk.onroad.roadmap.repository.LectureRepository;
import org.dongguk.onroad.roadmap.repository.WeekRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateRoadmapService implements CreateRoadmapUseCase {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserRepository userRepository;
    private final WeekRepository weekRepository;
    private final LectureRepository lectureRepository;

    private final UserService userService;
    private final LectureService lectureService;

    private final S3Util s3Util;

    @Override
    @Transactional
    public void execute(UUID userId, Long lectureId, MultipartFile file) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 교수 유효성 검사
        userService.validateProfessor(user);

        // 강의 조회
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 최초 생성 강의가 아니라면, 강의 상태 Loading으로 업데이트
        lectureService.updateStatusLoading(lecture, weekRepository.countByLecture(lecture));

        // PDF 파일 S3에 업로드
        String uploadFileUrl = s3Util.upload(file);

        // 이벤트 발행
        applicationEventPublisher.publishEvent(
                PdfEvent.builder()
                        .lectureId(lectureId)
                        .fileName(uploadFileUrl)
                        .build()
        );
    }


}
