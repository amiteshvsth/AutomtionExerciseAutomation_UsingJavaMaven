package API.dataFactory.user;

import API.dataObjects.request.user.UserRequestDO;

public class UserRequestDF {

    public static UserRequestDO setValidLoginDetails(){
        UserRequestDO userRequestDO = new UserRequestDO();
        userRequestDO.setEmail("amiteshvashishth@yopmail.com");
        userRequestDO.setPassword("12345678");
        return userRequestDO;
    }

    public static UserRequestDO setInvalidLoginDetails(){
        UserRequestDO userRequestDO = new UserRequestDO();
        userRequestDO.setEmail("amitesh@yopmail.com");
        userRequestDO.setPassword("12345ssjkls678");
        return userRequestDO;
    }
}
