package API.dataObjects.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDO {

    @JsonProperty("usertype")
    private UserTypeRequestDO usertype;
    @JsonProperty("category")
    private String category;
}