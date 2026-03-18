package API.dataObjects.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequestDO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("brand")
    private String brand;
}