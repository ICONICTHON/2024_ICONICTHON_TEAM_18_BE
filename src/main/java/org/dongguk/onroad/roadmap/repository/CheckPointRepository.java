package org.dongguk.onroad.roadmap.repository;

import org.dongguk.onroad.roadmap.domain.CheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {
}
