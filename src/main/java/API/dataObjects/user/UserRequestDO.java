package API.dataObjects.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDO {
    private String email;
    private String password;
}
