package ro.utcn.spet.a1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {
   // @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    private String body;
    private String username;
    private String date;

    List<Tag> tags;

    public Question(int id, String title, String body, String username, String date){
        this.id=id;
        this.title=title;
        this.body=body;
        this.username=username;
        this.date=date;
        this.tags=new ArrayList<Tag>();
    }
    public Question(String title, String body, String username, String date){
        this.title=title;
        this.body=body;
        this.username=username;
        this.date=date;
        this.tags=new ArrayList<Tag>();
    }

}
