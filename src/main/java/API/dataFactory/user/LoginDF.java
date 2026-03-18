package API.dataFactory.user;

import API.dataObjects.user.UserRequestDO;
import Functional.dataFactory.BaseDF;

public class LoginDF extends BaseDF {

    public static UserRequestDO getData(){
        UserRequestDO userRequestDO = new UserRequestDO();
        userRequestDO.setEmail(faker.internet().emailAddress());
        userRequestDO.setPassword(faker.internet().password());
        return userRequestDO;
    }
}
