package pl.saxatachi.kuchcik.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemCartDTO {
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private BigDecimal price;
}
