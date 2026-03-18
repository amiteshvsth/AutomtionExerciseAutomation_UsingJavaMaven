package API.dataObjects.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandsResponseDO {

    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("brands")
    private List<BrandRequestDO> brands;

}
