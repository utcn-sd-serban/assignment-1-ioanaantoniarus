package ro.utcn.spet.a1.repository.api;

import ro.utcn.spet.a1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User validateUser(String username, String password);

    User save(User user);

    Optional<User> findById(int id);

    void remove(User user);

    List<User> findAll();
}
