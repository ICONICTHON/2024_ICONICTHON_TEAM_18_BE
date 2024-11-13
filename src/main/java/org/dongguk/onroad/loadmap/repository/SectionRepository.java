package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
