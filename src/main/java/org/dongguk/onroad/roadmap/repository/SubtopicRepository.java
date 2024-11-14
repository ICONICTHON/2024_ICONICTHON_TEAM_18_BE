package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {

    @Query("SELECT s FROM Subtopic s " +
            "JOIN s.section se " +
            "JOIN se.week w " +
            "JOIN w.lecture l " +
            "WHERE l.id = :lectureId")
    List<Subtopic> findAllByLectureId(@Param("lectureId") Long lectureId);
}
