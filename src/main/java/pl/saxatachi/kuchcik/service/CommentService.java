package pl.saxatachi.kuchcik.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Comment;
import pl.saxatachi.kuchcik.repository.CommentRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
    public Comment addComment(Comment comment){
        LocalDateTime now = LocalDateTime.now();
        comment.setCreated(now);
        return commentRepository.save(comment);
    }
    public List<Comment> getAuthorComments(long id){
        return commentRepository.findAllAuthorComments(id);
    }
    public void deleteComment(long id){
        commentRepository.deleteById(id);
    }
    @Transactional
    public Comment editComment(Comment comment){
        Comment commentEdited = commentRepository.findById(comment.getId()).orElseThrow();
        commentEdited.setContent(comment.getContent());
        return comment;
    }

}
