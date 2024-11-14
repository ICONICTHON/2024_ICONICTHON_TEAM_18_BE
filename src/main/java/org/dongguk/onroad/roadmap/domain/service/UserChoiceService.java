package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.Choice;
import org.dongguk.onroad.roadmap.domain.UserChoice;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.stereotype.Service;

@Service
public class UserChoiceService {

    public UserChoice createUserChoice(
            User student,
            Choice choice
    ) {
        return UserChoice.builder()
                .student(student)
                .choice(choice)
                .build();
    }
}
