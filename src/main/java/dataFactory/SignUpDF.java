package dataFactory;

import dataObject.SignUpDO;
import enums.Country;
import enums.Title;

public class SignUpDF {

    public static SignUpDO fillSignUpDetails(){
        SignUpDO signUpDO = new SignUpDO();
        signUpDO.setTitle(Title.MR);
        signUpDO.setName("Amitesh");
        signUpDO.setPassword("Amitesh");
        signUpDO.setDay("12");
        signUpDO.setMonth("12");
        signUpDO.setYear("12");
        signUpDO.setNewsletter(true);
        signUpDO.setOption(true);

        signUpDO.setFirstName("Amitesh");
        signUpDO.setLastName("test");
        signUpDO.setCompany("Amitesh");
        signUpDO.setAddress1("Amitesh");
        signUpDO.setAddress2("Amitesh");
        signUpDO.setCountry(Country.USA);
        signUpDO.setState("Amitesh");
        signUpDO.setCity("Amitesh");
        signUpDO.setZipcode("Amitesh");
        signUpDO.setMobile("Amitesh");
        return signUpDO;
    }

}
