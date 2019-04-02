package ro.utcn.spet.a1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //@Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    //@OneToMany(fetch=FetchType.EAGER)
    //@JoinColumn
    List<Question> questions;

    public User(int id, String username, String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }
}
