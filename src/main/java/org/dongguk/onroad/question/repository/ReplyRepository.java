package org.dongguk.onroad.question.repository;

import org.dongguk.onroad.question.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByQuestionId(Long questionId);
}
