package pl.saxatachi.kuchcik.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="order_id")
    private Long orderId;
    @Column(name="order_item_id")
    private Long order_item_id;
}
