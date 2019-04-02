package ro.utcn.spet.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.a1.model.Answer;
import ro.utcn.spet.a1.repository.api.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcAnswerRepository implements AnswerRepository {

    private final JdbcTemplate template;

    @Override
    public Answer save(Answer answer) {
        if(answer.getId()!= 0){
            update(answer);
        }
        else{
            answer.setId(insert(answer));
        }
        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        List<Answer> answers=template.query("SELECT * FROM answer WHERE id= ?",
                new Object[]{ id },
                new AnswerMapper());
        return answers.isEmpty() ? Optional.empty() : Optional.of(answers.get(0));
    }

    @Override
    public void remove(Answer answer) {
        template.update("DELETE FROM answer WHERE id= ?",answer.getId());
    }

    @Override
    public List<Answer> findAll() {
        return template.query("SELECT * FROM answer", new AnswerMapper());
    }

    private int insert(Answer answer){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("answer");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("body", answer.getBody());
        data.put("username",answer.getUsername());
        data.put("date",answer.getDate());
        data.put("question_id",answer.getQuestionId());
        return insert.executeAndReturnKey(data).intValue();
    }

    public void update(Answer answer){
        template.update("UPDATE answer SET body=? WHERE id=?",
                answer.getBody(),answer.getId());
    }


}
