package ro.utcn.spet.a1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String body;
    private String username;
    private String date;
    private int questionId;

    public Answer(String body, String username, String date, int questionId) {
        this.body = body;
        this.username=username;
        this.date=date;
        this.questionId=questionId;
    }
}