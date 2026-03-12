package API.dataFactory.user;

import API.dataFactory.BaseDF;
import API.dataObjects.request.user.UserDO;
import API.dataObjects.request.user.UserRequestDO;

import java.util.UUID;

public class UserRequestDF extends BaseDF {

    public static UserRequestDO setValidLoginDetails(){
        UserRequestDO userRequestDO = new UserRequestDO();
        userRequestDO.setEmail("amiteshvashishth@yopmail.com");
        userRequestDO.setPassword("12345678");
        return userRequestDO;
    }

    public static UserRequestDO setInvalidLoginDetails(){
        UserRequestDO userRequestDO = new UserRequestDO();
        userRequestDO.setEmail(faker.internet().emailAddress());
        userRequestDO.setPassword(faker.internet().password());
        return userRequestDO;
    }

    public static UserDO setValidSignUpDetails(){
        UserDO userRequestDO = new UserDO();

        userRequestDO.setEmail(faker.internet().emailAddress());
        userRequestDO.setPassword(faker.internet().password(8,12));

        userRequestDO.setTitle("Mr");

        userRequestDO.setBirth_day(String.valueOf(faker.number().numberBetween(1,28)));
        userRequestDO.setBirth_month(String.valueOf(faker.number().numberBetween(1,12)));
        userRequestDO.setBirth_year(String.valueOf(faker.number().numberBetween(1985,2002)));

        userRequestDO.setFirst_name(faker.name().firstName());
        userRequestDO.setLast_name(faker.name().lastName());

        userRequestDO.setCompany(faker.company().name());

        userRequestDO.setAddress1(faker.address().streetAddress());
        userRequestDO.setAddress2(faker.address().secondaryAddress());

        userRequestDO.setCountry("India");
        userRequestDO.setState(faker.address().state());
        userRequestDO.setCity(faker.address().city());

        userRequestDO.setZipcode(faker.address().zipCode());
        userRequestDO.setMobile_number(faker.phoneNumber().cellPhone());

        return userRequestDO;
    }

    public static UserDO setInvalidSignUpDetails(){
        UserDO userRequestDO = new UserDO();

        userRequestDO.setEmail(faker.internet().emailAddress());
        userRequestDO.setPassword(faker.internet().password(8,12));

        userRequestDO.setFirst_name(faker.name().firstName());
        userRequestDO.setLast_name(faker.name().lastName());

        userRequestDO.setCountry("Unknown");

        return userRequestDO;
    }

}