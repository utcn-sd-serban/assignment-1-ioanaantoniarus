package ro.utcn.spet.a1.repository.memory;

import ro.utcn.spet.a1.model.Answer;
import ro.utcn.spet.a1.repository.api.AnswerRepository;

import java.util.*;

public class InMemoryAnswerRepository implements AnswerRepository {
    private int currentId=1;
    private final Map<Integer,Answer> data=new HashMap<Integer,Answer>();
    @Override
    public Answer save(Answer answer) {
        if(answer.getId()!=0){
            data.put(answer.getId(), answer);
        }
        else{
            answer.setId(currentId++);
            data.put(answer.getId(),answer);
        }
        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void remove(Answer answer) {
        data.remove(answer.getId());
    }

    @Override
    public List<Answer> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void update(Answer answer) {

    }
}
