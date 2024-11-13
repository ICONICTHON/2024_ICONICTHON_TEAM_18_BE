package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
