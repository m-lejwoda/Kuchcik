package pl.saxatachi.kuchcik.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="restaurant_opinions")
public class RestaurantOpinions {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="overall_rating")
    private Integer overall_rating;
    @Column(name="delivery_time")
    private Integer delivery_time;
    @Column(name="evaluation_of_service")
    private Integer evaluation_of_service;
    @Column(name="food_quality")
    private Integer food_quality;
    @Column(name="description")
    private String description;
    @Column(name="restaurantId")
    private Long restaurantId;
    @Column(name="userId")
    private Long userId;

}
