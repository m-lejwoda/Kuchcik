package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.City;
import pl.saxatachi.kuchcik.repository.CityRepository;
import pl.saxatachi.kuchcik.repository.IngredientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public City addCity(City city){
        return cityRepository.save(city);
    }
    public List<City> getCities(){
        return cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
