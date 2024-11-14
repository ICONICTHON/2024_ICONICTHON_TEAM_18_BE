package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {
}