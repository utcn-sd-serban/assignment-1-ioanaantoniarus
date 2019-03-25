package ro.utcn.spet.a1.repository.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.spet.a1.repository.api.QuestionRepository;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;
import ro.utcn.spet.a1.repository.api.TagRepository;
import ro.utcn.spet.a1.repository.api.UserRepository;

@Component
@ConditionalOnProperty(name = "a1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {
    private final InMemoryQuestionRepository repositoryQ = new InMemoryQuestionRepository();
    private final InMemoryUserRepository repositoryU= new InMemoryUserRepository();
    private final InMemoryTagRepository repositoryT=new InMemoryTagRepository();

    @Override
    public QuestionRepository createQuestionRepository() {
        return repositoryQ;
    }

    @Override
    public UserRepository createUserRepository() {
        return repositoryU;
    }

    @Override
    public TagRepository createTagRepository() {
        return repositoryT;
    }
}
