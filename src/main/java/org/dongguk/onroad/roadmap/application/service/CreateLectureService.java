package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.request.CreateLectureRequestDto;
import org.dongguk.onroad.roadmap.application.usecase.CreateLectureUseCase;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.roadmap.domain.service.LectureService;
import org.dongguk.onroad.roadmap.domain.service.UserLectureService;
import org.dongguk.onroad.roadmap.repository.LectureRepository;
import org.dongguk.onroad.roadmap.repository.UserLectureRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.service.UserService;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateLectureService implements CreateLectureUseCase {

    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;

    private final UserService userService;
    private final LectureService lectureService;
    private final UserLectureService userLectureService;

    @Override
    @Transactional
    public void execute(UUID userId, CreateLectureRequestDto requestDto) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 교수 유효성 검사
        userService.validateProfessor(user);

        // 강의 생성
        Lecture lecture = lectureService.createLecture(
                requestDto.title(),
                LocalDate.now().getYear(),
                requestDto.semester()
        );

        lectureRepository.save(lecture);

        // 교수를 강의에 참여시킴
        UserLecture userLecture = userLectureService.createUserLecture(lecture, user);
        userLectureRepository.save(userLecture);
    }

}
