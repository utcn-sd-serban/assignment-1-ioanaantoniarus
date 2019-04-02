package ro.utcn.spet.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.a1.exception.QuestionNotFoundException;
import ro.utcn.spet.a1.model.Answer;
import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.repository.api.QuestionRepository;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Question> listQuestions(){
        List<Question> questions=repositoryFactory.createQuestionRepository().findAll();
        questions.sort(Comparator.comparing(Question::getDate).reversed());
        return questions;
    }

    @Transactional
    public Question addQuestion(String title,String body, String username){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime= LocalDateTime.now();
        String date=localDateTime.format(formatter);
        return repositoryFactory.createQuestionRepository().save(new Question(title, body, username,date));
    }

    public Question findQuestion(int id){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question=repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        return question;
    }

    @Transactional
    public void updateText(int id, String text){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        question.setBody(text);
        repository.save(question);
    }

    @Transactional
    public void removeQuestion(int id){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        repository.remove(question);
    }

    @Transactional
    public List<Question> findByTitle(String title){
        return repositoryFactory.createQuestionRepository().findByTitle(title);
    }

    @Transactional
    public List<Question> findByTag(Tag tag){
        return repositoryFactory.createQuestionRepository().findByTag(tag);
    }

    @Transactional
    public Question addTagToQuestion(int id, Tag tag){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question=repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        List<Tag> tags=question.getTags();
        tags.add(tag);
        question.setTags(tags);
        repository.save(question);
        return question;
    }

    @Transactional
    public Question addAnswerToQuestion(int id, Answer answer){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question=repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        List<Answer> answers=question.getAnswers();
        answers.add(answer);
        question.setAnswers(answers);
        repository.save(question);
        return question;
    }
}
