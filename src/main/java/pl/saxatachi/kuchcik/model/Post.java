package pl.saxatachi.kuchcik.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import springfox.documentation.service.Representation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="post")
public class Post extends RepresentationModel<Post> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="author")
    private String author;
    @Column(name="created_date")
    private String created_date;
    @Column(name="created")
    private LocalDateTime created;
    @Column(name="commentId")
    private Long commentId;
    @Column(name="userId")
    private Long userId;
    @OneToMany
    @JoinColumn(name="postId",updatable = false,insertable = false)
    private List<Comment> comment;
    @OneToMany
    @JoinColumn(name="tagId",updatable = false,insertable = false)
    private List<Tag> tag;
}
