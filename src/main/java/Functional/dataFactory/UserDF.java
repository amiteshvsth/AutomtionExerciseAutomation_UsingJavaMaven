package Functional.dataFactory;

import Functional.dataObject.UserDO;
import java.util.UUID;

public class UserDF {

    private static final String DEFAULT_NAME = "Amitesh";
    private static final String DEFAULT_PASSWORD = "12345678";
    private static final String EXISTING_EMAIL = "amiteshvashishth@yopmail.com";
    private static final String INVALID_EMAIL = "amiteshvashishth@testmail.com";

    public static UserDO fillValidUserLoginDetails() {
        return createUser(EXISTING_EMAIL);
    }

    public static UserDO fillInvalidUserLoginDetails() {
        return createUser(INVALID_EMAIL);
    }

    public static UserDO fillValidUserSignUpDetails() {
        String uniqueEmail = "amiteshvashishth" + UUID.randomUUID() + "@yopmail.com";
        return createUser(uniqueEmail);
    }

    public static UserDO fillExistingUserSignUpDetails() {
        return createUser(EXISTING_EMAIL);
    }

    private static UserDO createUser(String email) {
        UserDO user = new UserDO();
        user.setName(DEFAULT_NAME);
        user.setEmail(email);
        user.setPassword(UserDF.DEFAULT_PASSWORD);
        return user;
    }
}