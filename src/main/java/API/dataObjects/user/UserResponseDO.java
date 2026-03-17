package API.dataObjects.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  class UserResponseDO {

    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("user")
    private UserDO user;
}
