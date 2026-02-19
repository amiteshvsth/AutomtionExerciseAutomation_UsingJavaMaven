package Functional.dataFactory;

import Functional.dataObject.UserDO;
import java.util.UUID;

public class UserDF {

    public static UserDO fillValidUserLoginDetails(){
        UserDO user = new UserDO();
        user.setName("Amitesh");
        user.setEmail("amiteshvashishth@yopmail.com");
        user.setPassword("12345678");
        return user;
    }

    public static UserDO fillInvalidUserLoginDetails(){
        UserDO user = new UserDO();
        user.setName("Amitesh");
        user.setEmail("amiteshvashishth@testmail.com");
        user.setPassword("12345678");
        return user;
    }

    public static UserDO fillValidUserSignUpDetails(){
        UserDO user = new UserDO();
        user.setName("Amitesh");
        user.setEmail("amiteshvashishth"+UUID.randomUUID().toString()+"@yopmail.com");
        user.setPassword("12345678");
        return user;
    }

    public static UserDO fillExistingUserSignUpDetails(){
        UserDO user = new UserDO();
        user.setName("Amitesh");
        user.setEmail("amiteshvashishth@yopmail.com");
        return user;
    }
}

