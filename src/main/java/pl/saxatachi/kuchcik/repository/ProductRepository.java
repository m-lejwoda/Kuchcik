package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product getById(Long id);
}
