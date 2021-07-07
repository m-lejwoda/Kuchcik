package pl.saxatachi.kuchcik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.saxatachi.kuchcik.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
