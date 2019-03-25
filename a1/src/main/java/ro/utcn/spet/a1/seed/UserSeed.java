package ro.utcn.spet.a1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.User;
import ro.utcn.spet.a1.repository.api.QuestionRepository;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;
import ro.utcn.spet.a1.repository.api.UserRepository;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed  implements CommandLineRunner {

    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        QuestionRepository repositoryQ = factory.createQuestionRepository();
        UserRepository repositoryU=factory.createUserRepository();

        if (repositoryU.findAll().isEmpty()){
            repositoryU.save(new User("ioana33", "ioana33"));
            repositoryU.save(new User("AnaBanana","blabla"));
            repositoryU.save(new User("Andrei00","0000"));
        }
    }
}
