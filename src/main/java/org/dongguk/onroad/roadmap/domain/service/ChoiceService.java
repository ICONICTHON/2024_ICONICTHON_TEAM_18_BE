package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.Choice;
import org.dongguk.onroad.roadmap.domain.Quiz;
import org.springframework.stereotype.Service;

@Service
public class ChoiceService {

    public Choice createChoice(String content, boolean isAnswer, Quiz quiz) {

        Choice choice = Choice.builder()
                .content(content)
                .isAnswer(isAnswer)
                .quiz(quiz)
                .build();

        quiz.getChoices().add(choice);

        return choice;
    }
}
