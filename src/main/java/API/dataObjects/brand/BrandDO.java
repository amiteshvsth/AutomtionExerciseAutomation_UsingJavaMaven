package API.dataObjects.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("brand")
    private String brand;
}