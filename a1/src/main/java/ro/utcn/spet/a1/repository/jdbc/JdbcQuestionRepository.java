package ro.utcn.spet.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.a1.model.Answer;
import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.repository.api.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {

    private final JdbcTemplate template;


    @Override
    public Question save(Question question) {
        if(question.getId()!= 0){
            update(question);
            updateTags(question);
        }
        else{
            question.setId(insert(question));
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        List<Question> questions=template.query("SELECT * FROM question WHERE id= ?",
                new Object[]{ id },
                new QuestionMapper());
        if(questions.isEmpty()){
            return Optional.empty();
        }
        else{
            questions.get(0).setTags(findTags(questions.get(0).getId()));
            questions.get(0).setAnswers(findQuestionAnswer(questions.get(0).getId()));
            return Optional.of(questions.get(0));
        }
    }

    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE id= ?",question.getId());
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions=template.query("SELECT * FROM question", new QuestionMapper());
        for(Question question: questions){
            question.setTags(findTags(question.getId()));
            question.setAnswers(findQuestionAnswer(question.getId()));
        }
        return questions;
    }

    @Override
    public List<Question> findByTitle(String title) {
        List<Question> questions= template.query("SELECT * FROM question WHERE title LIKE CONCAT('%' , ? , '%')",new Object[]{title}, new QuestionMapper());
        for(Question question: questions){
            question.setTags(findTags(question.getId()));
            question.setAnswers(findQuestionAnswer(question.getId()));
        }
        return questions;
    }

    @Override
    public List<Question> findByTag(Tag tag) {
        List<Question> questions=template.query("SELECT * from question INNER JOIN question_tag ON question.id=question_tag.id_question WHERE question_tag.id_tag=?", new Object[]{tag.getId()},
                new QuestionMapper());
        for(Question question:questions){
            question.setTags(findTags(question.getId()));
            question.setAnswers(findQuestionAnswer(question.getId()));
        }
        return questions;
    }

    private int insert(Question question){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("title", question.getTitle());
        data.put("body",question.getBody());
        data.put("username", question.getUsername());
        data.put("date", question.getDate());
        return insert.executeAndReturnKey(data).intValue();
    }

    private List<Tag> findTags(int id){
        return  template.query("SELECT * FROM tag INNER JOIN question_tag ON tag.id=question_tag.id_tag WHERE question_tag.id_question=? ",new Object[]{id},
                new TagMapper());
    }

    public List<Answer> findQuestionAnswer(int id) {
        List<Answer> answers=template.query("SELECT * FROM answer WHERE question_id=?",
                new Object[]{id},
                new AnswerMapper());
        return answers;
    }

    private void update(Question question){
        template.update("UPDATE question SET title=?, body=?, username=? WHERE id=?",
                question.getTitle(),question.getBody(),question.getUsername(),question.getId());
    }

    private void updateTags(Question question){
        List<Tag> tags=question.getTags();
        for(Tag tag:tags){
            List<Integer> list=template.query("SELECT id_tag FROM question_tag WHERE id_tag=? and id_question=?",new Object[]{tag.getId(),question.getId()},
                    (resultSet,i)->resultSet.getInt("id_tag"));
            if(list.isEmpty()){
                template.update("INSERT INTO question_tag (id_tag, id_question) VALUES (?,?)",tag.getId(),question.getId());
            }
        }
    }
}
