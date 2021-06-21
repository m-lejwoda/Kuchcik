package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Ingredient;
import pl.saxatachi.kuchcik.repository.IngredientRepository;
import java.util.List;
@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public List<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }
    public Ingredient addIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }
}
