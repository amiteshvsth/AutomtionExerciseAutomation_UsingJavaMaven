package Functional.dataFactory;

import Functional.dataObject.ContactUsDO;

public class ContactUsDF extends BaseDF {

    public static ContactUsDO getData(){
        ContactUsDO contactUs= new ContactUsDO();
        contactUs.setName(faker.name().fullName());
        contactUs.setEmail(faker.internet().emailAddress());
        contactUs.setSubject(faker.lorem().sentence(3));
        contactUs.setMessage(faker.lorem().paragraph());
        return contactUs;
    }
}

