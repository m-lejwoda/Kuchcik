package pl.saxatachi.kuchcik.model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    @Column(name="street")
    private String street;
    @Column(name= "city")
    private String city;
    @Column(name="zipcode")
    private String zipcode;
    @Column(name="housenumber")
    private String housenumber;
    @Column(name="flatnumber")
    private String flatnumber;
    @Column(name="phone")
    private Integer phone;
    @Column(name="loaded")
    private Boolean loaded;
    @Column(name="userId")
    private Long userId;
}
