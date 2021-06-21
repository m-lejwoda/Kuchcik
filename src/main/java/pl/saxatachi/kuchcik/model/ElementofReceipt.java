package pl.saxatachi.kuchcik.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class ElementofReceipt {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Setter(value = AccessLevel.NONE)
    @Column(name="id")
    private Long id;
    @Column(name="type")
    private String type;
    @Column(name="quantity")
    private Float quantity;
    @Column(name="receiptId")
    private Long receiptId;
    @ManyToMany
    private List<Ingredient> ingredient;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public Long getId() {
        return id;
    }
}
