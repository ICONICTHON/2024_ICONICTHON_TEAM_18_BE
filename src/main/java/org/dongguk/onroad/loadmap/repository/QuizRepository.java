package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
