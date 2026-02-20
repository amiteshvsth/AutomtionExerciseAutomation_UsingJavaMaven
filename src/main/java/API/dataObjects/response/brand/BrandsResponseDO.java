package API.dataObjects.response.brand;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandsResponseDO {

    private int responseCode;
    private List<BrandDO> brands;
}
