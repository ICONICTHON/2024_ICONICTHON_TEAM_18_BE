package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {

    @Query("SELECT ul FROM UserLecture ul WHERE ul.student = :student")
    Page<UserLecture> findByStudent(@Param("student") User student, Pageable pageable);

    @Query("SELECT ul FROM UserLecture ul WHERE ul.professor = :professor")
    Page<UserLecture> findByProfessor(@Param("professor") User professor, Pageable pageable);
  
    @EntityGraph(attributePaths = {"lecture"})
    @Query("SELECT ul FROM UserLecture ul " +
            "WHERE ul.student = :user OR ul.professor = :user"
    )
    List<UserLecture> findByStudentOrProfessor(@Param("user") User user);

    Optional<UserLecture> findByLectureId(Long lectureId);
}
