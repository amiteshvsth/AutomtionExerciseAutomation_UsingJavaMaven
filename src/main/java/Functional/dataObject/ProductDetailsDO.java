package Functional.dataObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private String category;

    @JsonProperty("price")
    private String price;

    @JsonProperty("availability")
    private String availability;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("brand")
    private String brand;
}