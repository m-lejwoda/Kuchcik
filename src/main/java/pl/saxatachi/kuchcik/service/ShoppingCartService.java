package pl.saxatachi.kuchcik.service;

import pl.saxatachi.kuchcik.exception.NotEnoughProductsInStockException;
import pl.saxatachi.kuchcik.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);


    void removeProduct(Product product);

    void removeWholeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();

}