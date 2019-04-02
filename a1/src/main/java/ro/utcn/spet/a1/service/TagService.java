package ro.utcn.spet.a1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.spet.a1.exception.TagNotFoundException;
import ro.utcn.spet.a1.model.Tag;
import ro.utcn.spet.a1.repository.api.RepositoryFactory;
import ro.utcn.spet.a1.repository.api.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Tag> listTags(){
        return repositoryFactory.createTagRepository().findAll();
    }

    @Transactional public Tag addTag(String name){
        Tag tag=repositoryFactory.createTagRepository().findByName(name);
        if(tag!=null){
            return tag;
        }
        else{
            return repositoryFactory.createTagRepository().save(new Tag(name));
        }
    }

    @Transactional
    public void removeQuestion(int id){
        TagRepository repository = repositoryFactory.createTagRepository();
        Tag tag = repository.findById(id).orElseThrow(TagNotFoundException::new);
        repository.remove(tag);
    }


}
