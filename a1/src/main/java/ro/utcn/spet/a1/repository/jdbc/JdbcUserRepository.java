package ro.utcn.spet.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.a1.model.User;
import ro.utcn.spet.a1.repository.api.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate template;

    @Override
    public User validateUser(String username, String password) {
        List<User> users=template.query("SELECT * FROM  user WHERE username= ? and password= ?",new Object[]{username,password},new UserMapper());
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User save(User user) {
        if(user.getId()!= 0){
            update(user);
        }
        else{
            user.setId(insert(user));
        }
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> users=template.query("SELECT * FROM user WHERE id= ?",
                new Object[]{ id },
                new UserMapper());
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public void remove(User user) {
        template.update("DELETE FROM user WHERE id= ?",user.getId());
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM user", new UserMapper());
    }

    private int insert(User user){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("user");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("username", user.getUsername());
        data.put("password",user.getPassword());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(User user){
        template.update("UPDATE FROM user SET username =?, password=? WHERE id= ?",
                user.getUsername(),user.getPassword(),user.getId());
    }

}
