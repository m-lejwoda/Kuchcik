package pl.saxatachi.kuchcik.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.saxatachi.kuchcik.model.Post;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("Select p From Post p left join fetch p.comment")
    List<Post> findAllPosts(Pageable page);
    List<Post> findAllByUserId(Long id);

}
