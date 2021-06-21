package pl.saxatachi.kuchcik.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.saxatachi.kuchcik.model.Ingredient;
import pl.saxatachi.kuchcik.model.Post;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    @Query("Select i  From Ingredient i where i.name = ?1")
    List<Ingredient> findIngredientbyName(String name);
}
