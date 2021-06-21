package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Comment;
import pl.saxatachi.kuchcik.model.Tag;
import pl.saxatachi.kuchcik.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    public List<Tag> getTags(){
        return tagRepository.findAll();
    }
    public Tag addTag(Tag tag){
        return tagRepository.save(tag);
    }



}
