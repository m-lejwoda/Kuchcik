package pl.saxatachi.kuchcik.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import pl.saxatachi.kuchcik.model.*;
import pl.saxatachi.kuchcik.repository.ReceiptRepository;
import pl.saxatachi.kuchcik.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kuchcik/v2/")
public class Kuchcikapiv2 {

    private final PostService postService;
    private final IngredientService ingredientService;
    private final CommentService commentService;
    private final ReceiptService receiptService;
    private final TagService tagService;

    @GetMapping("/")
    public String home() {
        return "Hello Kuchcik2!";
    }
    @GetMapping("/testposts")
    public List<Post> testPosts(){
        return postService.testPosts();
    }
    @GetMapping("/posts")
    public List<Post> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >= 0 ? page: 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
//        return postService.testPosts(pageNumber,sortDirection);
        return postService.testPosts();
    }
    @GetMapping("/posts/{id}")
    public Post getSinglePost(@PathVariable long id){
        return postService.getSinglePost(id);
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("/posts")
    public Post editPost(@RequestBody Post post) {
        return postService.editPost(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredient(){
        return ingredientService.getIngredients();
    }
    @PostMapping("/ingredients")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.addIngredient(ingredient);
    }
    @GetMapping("/comments")
    public List<Comment> getComments(){
        return commentService.getComments();
    }
    @PostMapping("/comments")
    public Comment postComment(Comment comment){
        return commentService.addComment(comment);
    }
    @GetMapping("/authorcomments/{id}")
    public List<Comment> getAuthorComments(@PathVariable long id){
        return commentService.getAuthorComments(id);
    }
    @GetMapping("/receipts")
    public List<Receipt> getReceipts(){
        return receiptService.displayReceipts();
    }
    @PostMapping("/receipts")
    public Optional<Receipt> addReceipt(@RequestBody Receipt receipt) {
        return receiptService.addReceipt(receipt);
    }
    @DeleteMapping("/receipts/{id}")
    public void deleteReceipt(@PathVariable long id) {
        receiptService.deleteReceipt(id);
    }
    @GetMapping("/tags")
    public List<Tag> getTags(){return tagService.getTags();}
    @PostMapping("/tags")
    public Tag postTag(@RequestBody Tag tag){return tagService.addTag(tag);}
//    @PostMapping("addtagstopost/{id}")
//    public Post addtagstopost(@PathVariable long id){
//        return postService.
//    }
}
