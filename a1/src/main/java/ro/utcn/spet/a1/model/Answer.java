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
    private int userId;
    private int questionId;
}
