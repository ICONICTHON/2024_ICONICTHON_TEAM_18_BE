package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {
}
