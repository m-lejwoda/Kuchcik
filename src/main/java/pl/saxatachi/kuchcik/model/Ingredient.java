package pl.saxatachi.kuchcik.model;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "Ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Setter(value = AccessLevel.NONE)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="country_of_origin")
    private String country_of_origin;
    @Column(name="price")
    private Float price;
    @Column(name="content")
    private String content;
    @Column(name="image_path")
    private String image_path;
//    @ManyToOne
//    @JoinColumn(name="elementingredient_id")
//    private ElementofReceipt elementofreceipt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
