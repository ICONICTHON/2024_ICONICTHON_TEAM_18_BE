package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.stereotype.Service;

@Service
public class UserLectureService {

    public UserLecture createUserLecture(Lecture lecture, User professor){
        return UserLecture.builder()
                .lecture(lecture)
                .professor(professor)
                .build();
    }
}
