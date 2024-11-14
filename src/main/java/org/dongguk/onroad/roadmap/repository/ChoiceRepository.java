package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
