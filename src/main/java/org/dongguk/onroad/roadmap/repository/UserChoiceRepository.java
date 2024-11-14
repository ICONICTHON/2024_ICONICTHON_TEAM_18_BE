package org.dongguk.onroad.roadmap.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.UserChoice;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {


    @Query("SELECT " +
            "(SELECT COUNT(q) FROM Quiz q WHERE q.checkPoint.subtopic.section.week.lecture = :lecture) AS totalQuizzes, " +
            "COUNT(DISTINCT uc.choice.quiz.id) AS attemptedQuizzes " +
            "FROM UserChoice uc " +
            "WHERE uc.student = :student AND uc.choice.quiz.checkPoint.subtopic.section.week.lecture = :lecture")
    ProgressProjection findProgressByStudentAndLecture(@Param("student") User student, @Param("lecture") Lecture lecture);



    interface ProgressProjection {
        int getTotalQuizzes();
        int getAttemptedQuizzes();
    }
}
