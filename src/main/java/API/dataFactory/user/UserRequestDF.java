package API.dataFactory.user;

import API.dataObjects.request.user.UserDO;
import API.dataObjects.request.user.UserRequestDO;

import java.util.UUID;

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

    public static UserDO setValidSignUpDetails(){
        UserDO userRequestDO = new UserDO();
        userRequestDO.setEmail("user_" + UUID.randomUUID() + "@yopmail.com");
        userRequestDO.setTitle("Mr");
        userRequestDO.setBirth_day("12");
        userRequestDO.setBirth_month("6");
        userRequestDO.setBirth_year("1999");
        userRequestDO.setFirst_name("Amitesh");
        userRequestDO.setLast_name("Vashishth");
        userRequestDO.setCompany("Amitesh Org.");
        userRequestDO.setAddress1("Nikol");
        userRequestDO.setAddress2("Ahmedabad");
        userRequestDO.setCountry("India");
        userRequestDO.setState("Gujarat");
        userRequestDO.setCity("Ahmedabad");
        userRequestDO.setZipcode("380001");
        return userRequestDO;
    }

    public static UserDO setInvalidSignUpDetails(){
        UserDO userRequestDO = new UserDO();
        userRequestDO.setEmail("amiteshvashishth@yopmail.com");
        userRequestDO.setPassword("12345678");
        userRequestDO.setTitle("Mr");
        userRequestDO.setBirth_day("15");
        userRequestDO.setBirth_month("9");
        userRequestDO.setBirth_year("2000");
        userRequestDO.setFirst_name("Rahul");
        userRequestDO.setLast_name("Mehta");
        userRequestDO.setCompany("Kalyan Org.");
        userRequestDO.setAddress1("Naroda");
        userRequestDO.setAddress2("Gujarat");
        userRequestDO.setCountry("Canada");
        userRequestDO.setState("Rajasthan");
        userRequestDO.setCity("jaipur");
        userRequestDO.setZipcode("390003");
        userRequestDO.setMobile_number("123456789");
        return userRequestDO;
    }

}