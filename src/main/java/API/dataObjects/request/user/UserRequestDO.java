package API.dataObjects.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDO {
    private String email;
    private String password;
}
