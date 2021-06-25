package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Ingredient;
import pl.saxatachi.kuchcik.model.Post;
import pl.saxatachi.kuchcik.model.Tag;
import pl.saxatachi.kuchcik.model.User;
import pl.saxatachi.kuchcik.repository.PostRepository;
import pl.saxatachi.kuchcik.repository.TagRepository;
import pl.saxatachi.kuchcik.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private static final int PAGE_SIZE = 20;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
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
        List<Tag> temp_tags = new ArrayList<Tag>();
        for(Tag tag: post.getTag()){
            List<Tag> tag_list = tagRepository.findTagByName(tag.getName());
            if(tag_list.isEmpty()){
                Tag create_tag = tagRepository.save(tag);
                temp_tags.add(create_tag);
            }
            else{
                temp_tags.add(tag_list.get(0));
            }
        }
        LocalDateTime now = LocalDateTime.now();
        post.setCreated(now);
        post.setTag(temp_tags);
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
    public List<Post> myPosts(String email){
        User user = userRepository.findUserByEmail(email);
        Long user_id = user.getId();
        System.out.println(user_id);
        return postRepository.findAllByUserId(user_id);

    }
}
