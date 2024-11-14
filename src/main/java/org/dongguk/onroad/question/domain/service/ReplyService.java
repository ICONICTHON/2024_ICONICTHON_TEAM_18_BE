package org.dongguk.onroad.question.domain.service;

import org.dongguk.onroad.question.domain.Question;
import org.dongguk.onroad.question.domain.Reply;
import org.dongguk.onroad.security.domain.mysql.User;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    public Reply createReply(User user, Question question, String content) {
        return Reply.builder()
                .content(content)
                .user(user)
                .question(question)
                .build();
    }
}
