package org.dongguk.onroad.roadmap.application.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.application.dto.request.ReadLectureOverviewResponseDto;
import org.dongguk.onroad.roadmap.application.usecase.ReadLectureOverviewUseCase;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.roadmap.domain.Week;
import org.dongguk.onroad.roadmap.repository.UserChoiceRepository;
import org.dongguk.onroad.roadmap.repository.UserLectureRepository;
import org.dongguk.onroad.roadmap.repository.WeekRepository;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.repository.mysql.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.dongguk.onroad.roadmap.repository.UserChoiceRepository.*;

@Service
@RequiredArgsConstructor
public class ReadLectureOverviewService implements ReadLectureOverviewUseCase {

    private final UserRepository userRepository;
    private final UserLectureRepository userLectureRepository;
    private final UserChoiceRepository userChoiceRepository;
    private final WeekRepository weekRepository;

    @Override
    @Transactional(readOnly = true)
    public ReadLectureOverviewResponseDto execute(Integer page, Integer size, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<UserLecture> userLectures = switch (user.getRole()) {
            case STUDENT -> userLectureRepository.findByStudent(user, pageable);
            case PROFESSOR -> userLectureRepository.findByProfessor(user, pageable);
        };

        List<ReadLectureOverviewResponseDto.LectureOverview> lectureOverviewList = userLectures.stream()
                .map(userLecture -> {
                    Lecture lecture = userLecture.getLecture();

                    // 진행률 계산
                    Integer progressRate = calculateProgressRate(user, lecture);

                    Week week = weekRepository.findIsSelectedByLecture(lecture)
                            .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

                    return ReadLectureOverviewResponseDto.LectureOverview.builder()
                            .id(lecture.getId())
                            .title(lecture.getTitle())
                            .professorName(userLecture.getProfessor().getName())
                            .currentWeek(week.getWeekIndex())
                            .progressRate(progressRate)
                            .build();
                })
                .toList();

        int totalPage = userLectures.getTotalPages();
        int currentPage = userLectures.getNumber() + 1;

        return ReadLectureOverviewResponseDto.of(
                lectureOverviewList,
                totalPage,
                currentPage
        );
    }


    private Integer calculateProgressRate(User user, Lecture lecture){

        if(user.getRole().equals(ESecurityRole.PROFESSOR)){
            return null;
        }

        ProgressProjection progressData = userChoiceRepository.findProgressByStudentAndLecture(user, lecture);
        int totalQuizzes = progressData.getTotalQuizzes();
        int attemptedQuizzes = progressData.getAttemptedQuizzes();
        return totalQuizzes > 0 ? (attemptedQuizzes * 100 / totalQuizzes) : 100;
    }
}
