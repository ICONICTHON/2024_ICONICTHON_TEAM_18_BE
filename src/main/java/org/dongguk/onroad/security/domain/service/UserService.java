package org.dongguk.onroad.security.domain.service;

import org.dongguk.onroad.core.exception.error.ErrorCode;
import org.dongguk.onroad.core.exception.type.CommonException;
import org.dongguk.onroad.security.domain.mysql.User;
import org.dongguk.onroad.security.domain.type.ESecurityRole;
import org.dongguk.onroad.security.info.CustomUserPrincipal;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public CustomUserPrincipal createCustomUserPrincipalByUser(User user) {
        return CustomUserPrincipal.create(user);
    }

    public User createUser(
            String serialId,
            String password,
            String name,
            ESecurityRole role
    ) {
        return User.builder()
                .serialId(serialId)
                .password(password)
                .name(name)
                .role(role)
                .build();
    }

    public void validateStudent(User user) {
        if (!user.getRole().equals(ESecurityRole.STUDENT)) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
    }

    public void validateProfessor(User user) {
        if (!user.getRole().equals(ESecurityRole.PROFESSOR)) {
            throw new CommonException(ErrorCode.ACCESS_DENIED);
        }
    }
}
