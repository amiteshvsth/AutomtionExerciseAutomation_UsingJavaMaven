package Functional.dataFactory;

import Functional.dataObject.UserDO;

public class UserDF extends BaseDF{


    public static UserDO getData() {
        UserDO user = new UserDO();

        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword("12345678");

        return user;
    }

}