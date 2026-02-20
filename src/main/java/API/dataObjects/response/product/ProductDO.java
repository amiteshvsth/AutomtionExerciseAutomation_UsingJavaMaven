package API.dataObjects.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDO {

    private int id;
    private String name;
    private String price;
    private String brand;
    private CategoryDO category;
}
