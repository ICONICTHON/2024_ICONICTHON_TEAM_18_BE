package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.type.ESemester;
import org.dongguk.onroad.roadmap.domain.type.EStatus;
import org.springframework.stereotype.Service;

@Service
public class LectureService {

    public Lecture createLecture(String title, Integer year, ESemester semester){
        return Lecture.builder()
                .title(title)
                .year(year)
                .semester(semester)
                .build();
    }

    public void validateLecture(Lecture lecture) {
        if (lecture.getStatus() == EStatus.EMPTY) {
            throw new CommonException(ErrorCode.INVALID_ARGUMENT);
        }
    }
}
