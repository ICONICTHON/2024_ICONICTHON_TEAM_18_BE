package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.roadmap.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {

    @Query("SELECT w FROM Week w WHERE w.isSelected = true AND w.lecture = :lecture")
    Optional<Week> findIsSelectedByLecture(Lecture lecture);
}
