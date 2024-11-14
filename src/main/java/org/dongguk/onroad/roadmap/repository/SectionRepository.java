package org.dongguk.onroad.roadmap.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.dongguk.onroad.roadmap.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("SELECT s FROM Section s " +
            "JOIN s.week w " +
            "JOIN w.lecture l " +
            "WHERE l.id = :lectureId")
    List<Section> findAllByLectureId(@Param("lectureId") Long lectureId);
}
