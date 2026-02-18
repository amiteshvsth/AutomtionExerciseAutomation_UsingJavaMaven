package dataObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDO {

    private String name;
    private String category;
    private String price;
    private String availability;
    private String condition;
    private String brand;
}


