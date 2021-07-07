package pl.saxatachi.kuchcik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import pl.saxatachi.kuchcik.exception.NotEnoughProductsInStockException;
import pl.saxatachi.kuchcik.model.OrderItem;
import pl.saxatachi.kuchcik.model.Product;
import pl.saxatachi.kuchcik.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
//        return products;
    }

//    public void checkout(){
//        System.out.println("checkout");
//    }
    @Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            product = productRepository.getOne(entry.getKey().getId());
//            product = productRepository.findOne(entry.getKey().getId());
            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
        }
//        System.out.println(products.keySet());
//        productRepository.save(products.keySet());
        productRepository.flush();
        products.clear();
    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
