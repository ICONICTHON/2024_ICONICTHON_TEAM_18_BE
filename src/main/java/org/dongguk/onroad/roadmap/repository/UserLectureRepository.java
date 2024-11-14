package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.UserLecture;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {
    Page<UserLecture> findByStudent(User student, Pageable pageable);
    Page<UserLecture> findByProfessor(User professor, Pageable pageable);
}
