package ro.utcn.spet.a1.repository.memory;

import ro.utcn.spet.a1.model.User;
import ro.utcn.spet.a1.repository.api.UserRepository;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private int currentId=1;
    private final Map<Integer,User> data=new HashMap<Integer,User>();

    @Override
    public User validateUser(String username, String password) {
        for(User user: data.values()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    @Override
    public synchronized User save(User user) {
        if(user.getId()!=0){
            data.put(user.getId(), user);
        }
        else{
            user.setId(currentId++);
            data.put(user.getId(),user);
        }
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(User user) {
        data.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }
}
