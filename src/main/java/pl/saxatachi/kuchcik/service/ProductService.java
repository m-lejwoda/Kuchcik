package pl.saxatachi.kuchcik.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.saxatachi.kuchcik.model.Product;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    Page<Product> findAllProductsPageable(Pageable pageable);

    Product getById(Long id);

}