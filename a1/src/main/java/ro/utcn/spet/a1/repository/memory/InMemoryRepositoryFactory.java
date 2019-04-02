package ro.utcn.spet.a1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.a1.repository.api.*;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {
    private final InMemoryQuestionRepository questionRepository = new InMemoryQuestionRepository();
    private final InMemoryUserRepository userRepository= new InMemoryUserRepository();
    private final InMemoryTagRepository tagRepository=new InMemoryTagRepository();
    private final InMemoryAnswerRepository answerRepository=new InMemoryAnswerRepository();

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepository;
    }

    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }

    @Override
    public TagRepository createTagRepository() {
        return tagRepository;
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return answerRepository;
    }
}
