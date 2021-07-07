package pl.saxatachi.kuchcik.model;

import lombok.Getter;
import lombok.Setter;
import pl.saxatachi.kuchcik.controller.dto.ItemCartDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="allorders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(name="userId")
    private Long userId;
    @Column(name= "total")
    private BigDecimal total;
    @OneToMany
    @JoinColumn(name="order_item_id",updatable = false,insertable = false)
    private List<OrderItem> orderitems;
//    @ManyToOne
//    @JoinColumn(name="address_id",updatable = false,insertable = false)
//    private Address address;
    @OneToMany
    @Column(name="address_id")
    private List<Address> address;

}
