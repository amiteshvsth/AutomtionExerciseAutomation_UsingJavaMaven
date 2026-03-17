package API.dataObjects.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDO {
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
}
