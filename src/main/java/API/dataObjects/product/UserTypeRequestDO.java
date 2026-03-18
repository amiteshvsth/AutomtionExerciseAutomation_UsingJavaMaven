package API.dataObjects.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTypeRequestDO {

    @JsonProperty("usertype")
    private String usertype;
}