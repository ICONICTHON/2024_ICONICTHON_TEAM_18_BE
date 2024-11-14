package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Lecture;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @EntityGraph(attributePaths = {"weeks"})
    Optional<Lecture> findWithWeeksById(Long lectureId);
}
