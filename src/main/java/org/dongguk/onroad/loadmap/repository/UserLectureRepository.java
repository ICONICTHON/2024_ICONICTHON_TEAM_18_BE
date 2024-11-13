package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.UserLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {
}
