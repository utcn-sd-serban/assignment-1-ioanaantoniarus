package ro.utcn.spet.a1.repository.api;

import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.Tag;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question question);

    Optional<Question> findById(int id);

    void remove(Question question);

    List<Question> findAll();

    List<Question> findByTitle(String title);

    List<Question> findByTag(Tag tag);

}
