package org.dongguk.onroad.security.repository.mysql;

import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findBySerialId(String serialId);

    Optional<User> findBySerialIdAndProvider(String serialId, ESecurityProvider provider);
}
