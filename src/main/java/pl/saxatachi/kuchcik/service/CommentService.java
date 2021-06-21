package pl.saxatachi.kuchcik.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Comment;
import pl.saxatachi.kuchcik.repository.CommentRepository;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }
    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }
    public List<Comment> getAuthorComments(long id){
        return commentRepository.findAllAuthorComments(id);
    }

}
