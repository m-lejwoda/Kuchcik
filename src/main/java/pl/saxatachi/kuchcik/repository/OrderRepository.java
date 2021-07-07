package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
