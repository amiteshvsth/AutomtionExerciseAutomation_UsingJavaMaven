package Functional.dataFactory;

import Functional.dataObject.UserDO;

public class UserDF extends BaseDF{

    private static final String DEFAULT_PASSWORD = "12345678";
    private static final String EXISTING_EMAIL = "amiteshvashishth@yopmail.com";

    public static UserDO fillValidUserLoginDetails() {
        return createUser(EXISTING_EMAIL);
    }

    public static UserDO fillInvalidUserLoginDetails() {
        return createUser(faker.internet().emailAddress());
    }

    public static UserDO fillValidUserSignUpDetails() {
        return createUser(generateUniqueEmail());
    }

    public static UserDO fillExistingUserSignUpDetails() {
        return createUser(EXISTING_EMAIL);
    }

    private static UserDO createUser(String email) {
        UserDO user = new UserDO();

        user.setName(faker.name().fullName());
        user.setEmail(email);
        user.setPassword(DEFAULT_PASSWORD);

        return user;
    }

    private static String generateUniqueEmail() {
        return "user_" + System.currentTimeMillis() + "@yopmail.com";
    }
}