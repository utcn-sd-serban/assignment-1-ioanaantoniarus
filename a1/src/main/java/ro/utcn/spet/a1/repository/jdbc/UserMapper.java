package ro.utcn.spet.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.a1.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"));
    }

}
