package Functional.dataFactory;

import Functional.dataObject.UserDO;

public class UserDF extends BaseDF{


    public static UserDO getData() {
        return createUser(faker.internet().emailAddress());
    }

    private static UserDO createUser(String email) {
        UserDO user = new UserDO();

        user.setName(faker.name().fullName());
        user.setEmail(email);
        user.setPassword("12345678");

        return user;
    }

}