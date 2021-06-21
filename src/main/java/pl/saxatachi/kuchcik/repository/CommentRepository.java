package pl.saxatachi.kuchcik.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.saxatachi.kuchcik.model.Comment;
import pl.saxatachi.kuchcik.model.Post;

import java.util.List;
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("Select c From Comment c WHERE c.userId = ?1")
    List<Comment> findAllAuthorComments(Long id);

}
