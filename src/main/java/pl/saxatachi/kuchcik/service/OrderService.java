package pl.saxatachi.kuchcik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saxatachi.kuchcik.model.Order;
import pl.saxatachi.kuchcik.model.OrderItem;
import pl.saxatachi.kuchcik.repository.OrderItemRepository;
import pl.saxatachi.kuchcik.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    public void addOrder(Order order){
        orderRepository.save(order);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
    public List<Order> allorders (){
        return orderRepository.findAll();
    }
}
