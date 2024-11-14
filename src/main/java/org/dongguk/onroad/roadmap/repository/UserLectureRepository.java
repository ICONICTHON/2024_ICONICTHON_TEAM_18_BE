package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {

    @EntityGraph(attributePaths = {"lecture"})
    @Query("SELECT ul FROM UserLecture ul " +
            "WHERE ul.student = :user OR ul.professor = :user"
    )
    List<UserLecture> findByStudentOrProfessor(@Param("user") User user);
}
