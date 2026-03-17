package API.dataObjects.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDO {

    @JsonProperty("usertype")
    private UserTypeDO usertype;
    @JsonProperty("category")
    private String category;
}