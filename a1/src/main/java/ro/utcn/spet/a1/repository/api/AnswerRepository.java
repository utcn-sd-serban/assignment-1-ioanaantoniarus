package ro.utcn.spet.a1.repository.api;

import ro.utcn.spet.a1.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Answer save(Answer answer);

    Optional<Answer> findById(int id);

    void remove(Answer answer);

    List<Answer> findAll();

    void update(Answer answer);
}
