package ro.utcn.spet.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.a1.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Question(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("body"),
                resultSet.getString("username"),
                resultSet.getString("date"));
    }
}
