package pl.saxatachi.kuchcik.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ShoppingCartDto {
    @JsonProperty
    private List<ItemCartDTO> item;
    @JsonProperty
    private BigDecimal total;

}
