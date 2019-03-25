package ro.utcn.spet.a1.repository.api;

import ro.utcn.spet.a1.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag);

    Optional<Tag> findById(int id);

    void remove(Tag tag);

    List<Tag> findAll();

    Tag findByName(String name);
}
