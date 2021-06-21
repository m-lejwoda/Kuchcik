package pl.saxatachi.kuchcik.model;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="post")
public class Post {
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
    @Column(name="commentId")
    private Long commentId;
    @OneToMany
    @JoinColumn(name="postId",updatable = false,insertable = false)
    private List<Comment> comment;
    @OneToMany
    @JoinColumn(name="tagId",updatable = false,insertable = false)
    private List<Tag> tag;
}
