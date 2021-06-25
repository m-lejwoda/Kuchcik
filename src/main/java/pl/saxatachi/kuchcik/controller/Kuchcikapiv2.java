package pl.saxatachi.kuchcik.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.saxatachi.kuchcik.model.*;
import pl.saxatachi.kuchcik.repository.ReceiptRepository;
import pl.saxatachi.kuchcik.service.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kuchcik/v2/")
public class Kuchcikapiv2 {

    private final PostService postService;
    private final IngredientService ingredientService;
    private final CommentService commentService;
    private final ReceiptService receiptService;
    private final TagService tagService;
    private final RestaurantService restaurantService;
    private final CityService cityService;
    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "Hello Kuchcik2!";
    }
    @GetMapping("/testposts")
    public List<Post> testPosts(){

        return postService.testPosts();
    }
    @GetMapping("/myposts")
    public List<Post> displaymyPosts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return postService.myPosts(username);


    }
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page != null && page >= 0 ? page: 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        List<Post> allposts = postService.testPosts();
//        allposts.forEach(post -> post.add(linkTo(Kuchcikapiv2.class).slash(post.getId())));
        return new ResponseEntity<>(allposts, HttpStatus.OK);
//        return postService.testPosts();
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<EntityModel<Post>> getSinglePost(@PathVariable long id){
        Link link = linkTo(Kuchcikapiv2.class).slash("posts").slash(id+1).withSelfRel();
        Post postById = postService.getSinglePost(id);
//        CollectionModel<Post> result = CollectionModel.of(postById,link);
        EntityModel<Post> result = EntityModel.of(postById,link);
        return new ResponseEntity<>(result,HttpStatus.OK);
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
    public Comment postComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable long id){commentService.deleteComment(id);}
    @PutMapping("/comments/{id}")
    public void editComment(@RequestBody Comment comment){commentService.editComment(comment);}
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
    public Tag postTag(@RequestBody Tag tag){
        return tagService.addTag(tag);
    }
    @DeleteMapping("/tags/{id}")
    public void deleteTag(@PathVariable long id){
        tagService.deleteTag(id);
    }
    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurant(){
        return restaurantService.displayRestaurants();
    }
    @PostMapping("/restaurant")
    public Restaurant postRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.addRestaurant(restaurant);
    }
    @PostMapping("/city")
    public City postCity(@RequestBody City city){
        return cityService.addCity(city);
    }
    @GetMapping("/city")
    public List<City> getCities(){
        return cityService.getCities();
    }
    @GetMapping("/restaurantwithopinions")
    public Restaurant getRestaurantwithOpinions(Long id){
        return restaurantService.displayRestaurantwithOpinions(id);
    }
    @GetMapping("/restaurantopinions")
    public List<RestaurantOpinions> getRestaurantOpinions(Long id){
        return restaurantService.displayRestaurantOpinions(id);
    }
    @PostMapping("/addrestaurantopinions")
    public RestaurantOpinions postRestaurantOpinion(@RequestBody RestaurantOpinions restaurantOpinions){
        return restaurantService.postRestaurantOpinion(restaurantOpinions);
    }
//    @PostMapping("addtagstopost/{id}")
//    public Post addtagstopost(@PathVariable long id){
//        return postService.
//    }
}
