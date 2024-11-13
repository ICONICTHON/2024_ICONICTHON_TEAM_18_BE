package org.dongguk.onroad.loadmap.repository;

import org.dongguk.onroad.loadmap.domain.CheckPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {
}
