package ro.utcn.spet.a1.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.spet.a1.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Tag(resultSet.getInt("id"),
                resultSet.getString("name"));
    }
}
