package ro.utcn.spet.a1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private int id;
    private String name;

    public Tag(String name){
        this.name=name;
    }
}
