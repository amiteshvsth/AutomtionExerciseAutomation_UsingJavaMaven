package Functional.dataFactory;

import Functional.dataObject.UserDO;
import Functional.utilities.Constants;

public class UserDF extends BaseDF{


    public static UserDO getData() {
        UserDO user = new UserDO();

        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(Constants.CONSTANT_PASSWORD);

        return user;
    }

}