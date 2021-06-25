package pl.saxatachi.kuchcik.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.City;
import pl.saxatachi.kuchcik.model.Restaurant;
import pl.saxatachi.kuchcik.model.RestaurantOpinions;
import pl.saxatachi.kuchcik.repository.CityRepository;
import pl.saxatachi.kuchcik.repository.RestaurantOpinionsRepository;
import pl.saxatachi.kuchcik.repository.RestaurantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CityRepository cityRepository;
    private final RestaurantOpinionsRepository restaurantOpinionsRepository;
    public Restaurant addRestaurant(Restaurant restaurant){
        City temp_city = cityRepository.findCityByName(restaurant.getCities().getName());
        restaurant.setCities(temp_city);
        return restaurantRepository.save(restaurant);
    }
    public List<RestaurantOpinions> displayRestaurantOpinions(Long id){
        return restaurantOpinionsRepository.findRestaurantOpinionsByRestaurantId(id);
    }
    public Restaurant displayRestaurantwithOpinions(Long id){
        return restaurantRepository.findAllRestaurants(id);
    }
    public RestaurantOpinions postRestaurantOpinion(RestaurantOpinions restaurantOpinion){
        return restaurantOpinionsRepository.save(restaurantOpinion);
    }
    public List<Restaurant> displayRestaurants(){
        return restaurantRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
    }
}
