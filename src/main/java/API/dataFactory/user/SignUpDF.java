package API.dataFactory.user;

import API.dataObjects.user.UserDetailsRequestDO;
import Functional.dataFactory.BaseDF;

public class SignUpDF extends BaseDF {

    public static UserDetailsRequestDO getData(){
        UserDetailsRequestDO userRequestDO = new UserDetailsRequestDO();

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

}
