package org.dongguk.onroad.roadmap.domain.service;

import org.dongguk.onroad.roadmap.domain.CheckPoint;
import org.dongguk.onroad.roadmap.domain.Quiz;
import org.dongguk.onroad.roadmap.domain.type.EQuizType;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    public Quiz createQuiz(EQuizType quizType, String content, CheckPoint checkPoint) {
        Quiz quiz = Quiz.builder()
                .type(quizType)
                .content(content)
                .checkPoint(checkPoint)
                .build();

        checkPoint.updateQuiz(quiz);

        return quiz;
    }
}
