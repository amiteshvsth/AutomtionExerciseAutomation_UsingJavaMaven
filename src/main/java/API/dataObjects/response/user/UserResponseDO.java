package API.dataObjects.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDO {

    private int responseCode;
    private UserDO user;
}
