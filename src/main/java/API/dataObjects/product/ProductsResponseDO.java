package API.dataObjects.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductsResponseDO {

    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("products")
    private List<ProductDO> products;
}
