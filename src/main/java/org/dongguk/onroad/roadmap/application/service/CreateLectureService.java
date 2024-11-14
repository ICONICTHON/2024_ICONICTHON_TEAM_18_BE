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
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateLectureService implements CreateLectureUseCase {

    private final LectureService lectureService;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;
    private final UserLectureService userLectureService;

    @Override
    @Transactional
    public void execute(UUID userId, CreateLectureRequestDto requestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        if(!user.getRole().equals(ESecurityRole.PROFESSOR)){
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }

        Lecture lecture = lectureService.createLecture(
                requestDto.title(),
                LocalDate.now().getYear(),
                requestDto.semester()
        );

        lectureRepository.save(lecture);

        UserLecture userLecture = userLectureService.createUserLecture(lecture, user);
        userLectureRepository.save(userLecture);
    }

}
