package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {
}
