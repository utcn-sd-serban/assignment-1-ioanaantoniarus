package ro.utcn.spet.a1.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.repository.api.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) {
        if(tag.getId()!= 0){
            update(tag);
        }
        else{
            tag.setId(insert(tag));
        }
        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags=template.query("SELECT * FROM tag WHERE id= ?",
                new Object[]{ id },
                new TagMapper());
        return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
    }

    @Override
    public void remove(Tag tag) {
        template.update("DELETE FROM tag WHERE id= ?",tag.getId());
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM tag", new TagMapper());
    }

    @Override
    public Tag findByName(String name) {
        List<Tag> tags=template.query("SELECT * FROM tag where name=?",new Object[]{name},new TagMapper());
        return tags.isEmpty() ? null : tags.get(0);
    }

    private int insert(Tag tag){
        SimpleJdbcInsert insert= new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.setGeneratedKeyName("id");
        Map<String, Object> data= new HashMap<>();
        data.put("name", tag.getName());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Tag tag){
        template.update("UPDATE FROM tag SET name =? WHERE id= ?",
                tag.getName(),tag.getId());
    }
}
