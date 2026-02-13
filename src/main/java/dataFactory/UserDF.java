package dataFactory;

import dataObject.UserDO;
import java.util.UUID;

public class UserDF {

    public static UserDO createUser(){
        String unique = UUID.randomUUID().toString().substring(0,6);
        UserDO user = new UserDO();
        user.setName("Amitesh");
        user.setEmail("amitesh@test.com");
        user.setPassword("12345");
        return user;
    }
}

