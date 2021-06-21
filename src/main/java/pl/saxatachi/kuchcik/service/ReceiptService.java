package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.ElementofReceipt;
import pl.saxatachi.kuchcik.model.Ingredient;
import pl.saxatachi.kuchcik.model.Receipt;
import pl.saxatachi.kuchcik.repository.ElementofReceiptRepository;
import pl.saxatachi.kuchcik.repository.IngredientRepository;
import pl.saxatachi.kuchcik.repository.ReceiptRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptService {
    private final ReceiptRepository receiptRepository;
    private final ElementofReceiptRepository elementofReceiptRepository;
    private final IngredientRepository ingredientRepository;
    public void deleteReceipt(Long id){
        receiptRepository.deleteById(id);
    }
    public List<Receipt> displayReceipts(){
        return receiptRepository.findAll();
    }
    public Optional<Receipt> addReceipt(Receipt receipt){
        Receipt temp_receipt = receiptRepository.save(receipt);
        List<ElementofReceipt> temp_elements_of_receipt = new ArrayList<ElementofReceipt>();
        List<Ingredient> list_ingredients = new ArrayList<Ingredient>();
        for (ElementofReceipt element: receipt.getElementofreceipts()){
            for(Ingredient ingredient: element.getIngredient())
            {
                if(ingredientRepository.findIngredientbyName(ingredient.getName()).isEmpty()){
                    ingredientRepository.save(ingredient);
                    list_ingredients.add(ingredientRepository.findIngredientbyName(ingredient.getName()).get(0));
                }else{
                    list_ingredients.add(ingredientRepository.findIngredientbyName(ingredient.getName()).get(0));
                }
//                element.setReceiptId(temp_receipt.getId());
//
//                element.setIngredient(list_ingredients);
//                ElementofReceipt el_receipt = elementofReceiptRepository.save(element);
//                temp_elements_of_receipt.add(element);
            }
            element.setReceiptId(temp_receipt.getId());
            element.setIngredient(list_ingredients);
            elementofReceiptRepository.save(element);
            temp_elements_of_receipt.add(element);
//            temp_receipt.setElementofreceipts(temp_elements_of_receipt);
//            ElementofReceipt el_receipt = elementofReceiptRepository.save(element);
        }
        System.out.println(temp_elements_of_receipt);
        temp_receipt.setElementofreceipts(temp_elements_of_receipt);
        return receiptRepository.findById(temp_receipt.getId());
    }

}
