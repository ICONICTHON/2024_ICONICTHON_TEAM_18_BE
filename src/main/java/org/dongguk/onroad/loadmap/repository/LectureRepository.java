package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
