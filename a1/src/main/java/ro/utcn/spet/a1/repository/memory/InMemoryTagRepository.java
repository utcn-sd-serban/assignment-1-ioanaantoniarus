package ro.utcn.spet.a1.repository.memory;

import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.repository.api.TagRepository;

import java.util.*;

public class InMemoryTagRepository implements TagRepository {
    private int currentId=1;
    private final Map<Integer,Tag> data=new HashMap<Integer,Tag>();

    @Override
    public synchronized Tag save(Tag tag) {
        if(tag.getId()!=0){
            data.put(tag.getId(),tag);
        }else{
            tag.setId(currentId++);
            data.put(tag.getId(),tag);
        }
        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(Tag tag) {
        data.remove(tag.getId());
    }

    @Override
    public List<Tag> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Tag findByName(String name) {
        for(Tag tag:data.values()){
            if(tag.getName().equals(name)){
                return tag;
            }
        }
        return null;
    }
}
