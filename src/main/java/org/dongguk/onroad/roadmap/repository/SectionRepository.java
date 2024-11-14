package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}