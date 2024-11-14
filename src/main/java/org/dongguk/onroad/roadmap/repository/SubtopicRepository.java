package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {
}
