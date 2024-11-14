package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
