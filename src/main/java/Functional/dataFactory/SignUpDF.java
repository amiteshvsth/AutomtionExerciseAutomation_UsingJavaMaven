package Functional.dataFactory;

import Functional.dataObject.SignUpDO;
import Functional.enums.Country;
import Functional.enums.Title;

import java.time.Month;

public class SignUpDF extends BaseDF{

    public static SignUpDO getData(){
        SignUpDO signUpDO = new SignUpDO();
        signUpDO.setTitle(Title.MR);
        signUpDO.setName(faker.name().fullName());
        signUpDO.setPassword(faker.internet().password(8,12));

        signUpDO.setDay(String.valueOf(faker.number().numberBetween(1,28)));
        Month month = Month.of(faker.number().numberBetween(1, 12));
        String formattedMonth = month.name().charAt(0)
                + month.name().substring(1).toLowerCase();

        signUpDO.setMonth(formattedMonth);
        signUpDO.setYear(String.valueOf(faker.number().numberBetween(1985,2004)));
        signUpDO.setNewsletter(true);
        signUpDO.setOption(true);
        signUpDO.setFirstName(faker.name().firstName());
        signUpDO.setLastName(faker.name().lastName());
        signUpDO.setCompany(faker.company().name());
        signUpDO.setAddress1(faker.address().streetAddress());
        signUpDO.setAddress2(faker.address().secondaryAddress());

        signUpDO.setCountry(Country.INDIA);
        signUpDO.setState(faker.address().state());
        signUpDO.setCity(faker.address().city());
        signUpDO.setZipcode(faker.address().zipCode());
        signUpDO.setMobile(faker.phoneNumber().cellPhone());
        return signUpDO;
    }
}
