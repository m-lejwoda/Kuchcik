package pl.saxatachi.kuchcik.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name="cityId",updatable = false,insertable = false)
    private City cities;
    @OneToMany
    @JoinColumn(name="restaurantId",updatable = false,insertable = false)
    private List<RestaurantOpinions> restaurants;
}
