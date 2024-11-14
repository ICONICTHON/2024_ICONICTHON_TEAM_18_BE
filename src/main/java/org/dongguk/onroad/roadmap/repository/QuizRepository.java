package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT q FROM Quiz q " +
            "JOIN q.checkPoint cp " +
            "JOIN cp.subtopic s " +
            "WHERE s.id = :subtopicId"
    )
    List<Quiz> findAllBySubtopicId(@Param("subtopicId") Long subtopicId);
}
