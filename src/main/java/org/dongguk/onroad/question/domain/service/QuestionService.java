package org.dongguk.onroad.question.domain.service;

import org.dongguk.onroad.question.domain.Question;
import org.dongguk.onroad.roadmap.domain.Lecture;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    public Question createQuestion(
            String title,
            String content,
            Integer weekIndex,
            Lecture lecture,
            User student
    ) {
        return Question.builder()
                .title(title)
                .content(content)
                .weekIndex(weekIndex)
                .lecture(lecture)
                .student(student)
                .build();
    }
}
