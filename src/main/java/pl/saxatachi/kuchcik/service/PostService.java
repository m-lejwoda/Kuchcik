package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Post;
import pl.saxatachi.kuchcik.model.Tag;
import pl.saxatachi.kuchcik.repository.PostRepository;
import pl.saxatachi.kuchcik.repository.TagRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id")
                )
        );
    }
    public List<Post> testPosts(){
        return postRepository.findAll();
    }
    public Post getSinglePost(long id){
        return postRepository.findById(id).orElseThrow();
    }
    public Post addPost(Post post) {
        return postRepository.save(post);
    }
//    public Post addTagtoPost(long id){
//        Tag tag = tagRepository.getOne()
//        Post post = postRepository.findById(id).get();
//        List<Tag> tags = post.getTag();
//        tags.add()
//        post.setTag();
//
//
//    }
    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();
        postEdited.setTitle(post.getTitle());
        postEdited.setDescription(post.getDescription());
        return postEdited;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
