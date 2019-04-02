package ro.utcn.spet.a1.service;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.User;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;
import ro.utcn.spet.a1.repository.memory.InMemoryRepositoryFactory;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionServiceUnitTests {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createUserRepository().save(new User(1,"ioana33","ioana33"));
        factory.createUserRepository().save(new User(2,"AnaBanana","ana33"));
        factory.createQuestionRepository().save(new Question(1, "Question1", "This is question 1", "ioana33","2019-03-23T16:12"));
        factory.createQuestionRepository().save(new Question(2, "Question2", "Second question", "AnaBanana","2019-03-24T20:55"));
        return factory;
    }

    @Test
    public void testLoginWithExistigUser(){
        RepositoryFactory factory = createMockedFactory();
        UserService service = new UserService(factory);

        User user=service.validateUser("ioana33","ioana33");

        Assert.assertEquals("ioana33",user.getUsername());
        Assert.assertEquals("ioana33",user.getPassword());
    }


    @Test
    public void testLoginWithNonexistingUser(){
        RepositoryFactory factory = createMockedFactory();
        UserService service = new UserService(factory);
        User user=service.validateUser("ioana33","ioanaaa");

        Assert.assertEquals(null,user);
    }

    @Test
    public void testAddQuestion(){
        RepositoryFactory factory = createMockedFactory();
        QuestionService service = new QuestionService(factory);

        service.addQuestion("q3","question3","ioana33");

        Assert.assertEquals(3, factory.createQuestionRepository().findAll().size());
        Assert.assertTrue(factory.createQuestionRepository().findById(3).isPresent());
    }

    @Test
    public void testRemoveWorksWithExistingId() {
        RepositoryFactory factory = createMockedFactory();
        QuestionService service = new QuestionService(factory);

        service.removeQuestion(1);

        Assert.assertEquals(1, factory.createQuestionRepository().findAll().size());
        Assert.assertTrue(factory.createQuestionRepository().findById(2).isPresent());
    }

    @Test
    public void testListQuestions(){
        RepositoryFactory factory = createMockedFactory();
        QuestionService service = new QuestionService(factory);

        List<Question> questions=service.listQuestions();
        Assert.assertEquals(2,questions.size());
        Assert.assertEquals(2,questions.get(0).getId());
    }

    @Test
    public void testSearchByText(){
        RepositoryFactory factory = createMockedFactory();
        QuestionService service = new QuestionService(factory);

        List<Question> questions=service.findByTitle("Question1");

        Assert.assertEquals(1,questions.size());
        Assert.assertEquals(1,questions.get(0).getId());
    }
}
