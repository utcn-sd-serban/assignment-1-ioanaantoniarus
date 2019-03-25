package ro.utcn.spet.a1.repository.memory;

import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.repository.api.QuestionRepository;

import java.util.*;

public class InMemoryQuestionRepository implements QuestionRepository {
    private int currentId=1;
    private final Map<Integer,Question> data=new HashMap<Integer,Question>();

    @Override
    public synchronized Question save(Question question) {
        if(question.getId()!=0){
            data.put(question.getId(), question);
        }
        else{
            question.setId(currentId++);
            data.put(question.getId(),question);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public synchronized void remove(Question question) {
        data.remove(question.getId());
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public List<Question> findByTitle(String title) {
        List<Question> questions=new ArrayList<Question>();
        for(Question question: data.values()){
            if(question.getTitle().toLowerCase().contains(title.toLowerCase())){
                questions.add(question);
            }
        }
        return questions;
    }

    @Override
    public List<Question> findByTag(Tag tag) {
        List<Question> questions=new ArrayList<Question>();
        for(Question question:data.values()){
            if(question.getTags().contains(tag)){
                questions.add(question);
            }
        }
        return questions;
    }


}
