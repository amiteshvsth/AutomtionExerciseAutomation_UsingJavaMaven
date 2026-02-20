package API.dataObjects.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductsResponseDO {

    private int responseCode;
    private List<ProductDO> products;
}
