package pl.saxatachi.kuchcik.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.saxatachi.kuchcik.model.Post;
import pl.saxatachi.kuchcik.model.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    @Query("Select r From Restaurant r left join fetch r.restaurants where r.id = ?1")
    Restaurant findAllRestaurants(Long id);
}
