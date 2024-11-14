package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    @Query("SELECT c FROM Choice c " +
            "JOIN c.quiz q " +
            "JOIN q.checkPoint cp " +
            "JOIN cp.subtopic s " +
            "WHERE s.id = :subtopicId"
    )
    List<Choice> findAllBySubtopicId(@Param("subtopicId") Long subtopicId);
}
