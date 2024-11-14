package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.response.ReadLectureBriefResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadLectureBriefUseCase;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.roadmap.domain.type.EStatus;
import org.dongguk.onroad.roadmap.repository.UserLectureRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadLectureBriefService implements ReadLectureBriefUseCase {

    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadLectureBriefResponseDto execute(UUID userId) {

        // 유저 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        // 유저가 수강한 강의 객체 리스트 조회
        List<UserLecture> userLecture = userLectureRepository.findByStudentOrProfessor(user);

        // 강의 객체 리스트 조회
        List<Lecture> lectureList = userLecture.stream()
                .map(UserLecture::getLecture)
                .filter(lecture -> lecture.getStatus() == EStatus.COMPLETED)
                .toList();

        return ReadLectureBriefResponseDto.fromEntities(lectureList);
    }
}
