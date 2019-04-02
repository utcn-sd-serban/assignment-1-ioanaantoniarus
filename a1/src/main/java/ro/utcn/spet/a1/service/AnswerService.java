package ro.utcn.spet.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.a1.exception.AnswerNotFoundException;
import ro.utcn.spet.a1.model.Answer;
import ro.utcn.spet.a1.repository.api.AnswerRepository;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Answer> listAnswers(){
        List<Answer> answers=repositoryFactory.createAnswerRepository().findAll();
        return answers;
    }

    @Transactional
    public String editAnswer(int id, String body, String username){
        AnswerRepository repository=repositoryFactory.createAnswerRepository();
        Answer answer=repository.findById(id).orElseThrow(AnswerNotFoundException::new);
        if(answer.getUsername().equals(username)){
            answer.setBody(body);
            repository.update(answer);
        }
        else{
            return "You can not edit another user's answer!";
        }
        return "The answer was edited successfully!";
    }

    @Transactional
    public Answer addAnswer(String body, String username, int questionId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime= LocalDateTime.now();
        String date=localDateTime.format(formatter);
        return repositoryFactory.createAnswerRepository().save(new Answer(body,username,date,questionId));
    }

    @Transactional
    public String deleteAnswer(int id, String username){
        AnswerRepository repository=repositoryFactory.createAnswerRepository();
        Answer answer=repository.findById(id).orElseThrow(AnswerNotFoundException::new);
        if(answer.getUsername().equals(username)){
            repository.remove(answer);
        }
        else{
            return "You can not delete someone else's answer!";
        }
        return "Answer successfully deleted!";
    }
}
