package pl.saxatachi.kuchcik.repository;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.saxatachi.kuchcik.model.City;
import pl.saxatachi.kuchcik.model.Comment;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {
//    @Query("Select c From City c WHERE c.name = ?1")
    public City findCityByName(String name);
}
