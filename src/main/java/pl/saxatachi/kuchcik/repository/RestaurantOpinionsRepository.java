package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.RestaurantOpinions;

import java.util.List;

public interface RestaurantOpinionsRepository extends JpaRepository<RestaurantOpinions,Long> {
    public List<RestaurantOpinions> findRestaurantOpinionsByRestaurantId(Long id);
}
