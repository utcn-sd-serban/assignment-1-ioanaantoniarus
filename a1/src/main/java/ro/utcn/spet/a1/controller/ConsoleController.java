package ro.utcn.spet.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.spet.a1.exception.QuestionNotFoundException;
import ro.utcn.spet.a1.exception.TagNotFoundException;
import ro.utcn.spet.a1.model.Question;
import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.model.User;
import ro.utcn.spet.a1.service.QuestionService;
import ro.utcn.spet.a1.service.TagService;
import ro.utcn.spet.a1.service.UserService;

import java.util.Scanner;


@Component
@RequiredArgsConstructor
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final QuestionService questionService;
    private final TagService tagService;
    private User user;
    @Override
    public void run(String... args) throws Exception {
        print("Welcome!");
        boolean done = false;
        while (!done) {
            print("Enter command: ");
            String command = scanner.next().trim();
            try {
                done = handleCommand(command);
            } catch (QuestionNotFoundException questionNotFoundException) {
                print("The student with the given ID was not found!");
            }

        }
    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "login":
                handleLogin();
                return false;
            case "list":
                handleList();
                return false;
            case "add":
                handleAdd();
                return false;
            case "remove":
                handleRemove();
                return false;
            case "filtertext":
                handleFilterText();
                return false;
            case "filtertag":
                handleFilterTag();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    private void handleLogin() {
        print("Username:");
        String username = scanner.next().trim();
        print("password:");
        String password = scanner.next().trim();
        this.user = userService.validateUser(username, password);
        if(null != user) {
            print("Welcome user: " + user.getUsername() + ".");
        }else{
            print("Wrong username or password");
        }
    }

    private void handleList() {
        questionService.listQuestions().forEach(s -> print(s.toString()));
    }

    private void handleAdd() {
        print("Title: ");
        String title = scanner.next().trim();
        print("Text: ");
        String body = scanner.next().trim();
        String username = user.getUsername();
        Question question = questionService.addQuestion(title,body, username);
        print("Created question: " + question + ".");
        print("Add tags: ");
        print("If you want to stop adding tags, type finish");
        boolean finish=false;
        while(!finish){
            print("Enter tag: ");
            String tag=scanner.next().trim();
            try {
                finish = handleAddTag(tag, question.getId());
            } catch (TagNotFoundException tagNotFoundException) {
                print("The student with the given ID was not found!");
            }

        }
    }

    private void handleRemove() {
        print("Question ID:");
        int id = scanner.nextInt();
        questionService.removeQuestion(id);
    }

    private void handleFilterText(){
        print("Search text: ");
        String text=scanner.next().trim();
        questionService.findByTitle(text).forEach(s->print(s.toString()));
    }

    private boolean handleAddTag(String tag, int id){
        switch (tag){
            case "finish":
                return true;
            default:
                questionService.addTagToQuestion(id,tagService.addTag(tag));
                return false;

        }
    }
    private void handleFilterTag(){
        print("Search tag: ");
        String text=scanner.next().trim();
        Tag tag=tagService.addTag(text);
        questionService.findByTag(tag).forEach(s->print(s.toString()));
    }

    private void print(String value) {
        System.out.println(value);
    }
}
