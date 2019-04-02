package ro.utcn.spet.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.a1.model.User;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RepositoryFactory repositoryFactory;


    @Transactional
    public User validateUser(String username, String password){
        return repositoryFactory.createUserRepository().validateUser(username,password);
    }

}
