package pl.saxatachi.kuchcik.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="postId")
    private Long postId;
    @Column(name="userId")
    private Long userId;
    @Column(name="content")
    private String content;
    @Column(name="created")
    private LocalDateTime created;

}
