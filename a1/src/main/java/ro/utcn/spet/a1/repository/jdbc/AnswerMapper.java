package ro.utcn.spet.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.a1.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Answer(resultSet.getInt("id"),
                resultSet.getString("body"),
                resultSet.getString("username"),
                resultSet.getString("date"),
                resultSet.getInt("question_id"));
    }
}
