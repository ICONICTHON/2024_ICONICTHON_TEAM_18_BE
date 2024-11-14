package org.dongguk.onroad.security.domain.service;

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
            throw new IllegalArgumentException("유저의 권한이 학생이 아닙니다.");
        }
    }

    public void validateProfessor(User user) {
        if (!user.getRole().equals(ESecurityRole.PROFESSOR)) {
            throw new IllegalArgumentException("유저의 권한이 교수가 아닙니다.");
        }
    }
}
