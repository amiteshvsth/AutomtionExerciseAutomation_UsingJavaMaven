package Functional.dataFactory;

import Functional.dataObject.ContactUsDO;

public class ContactUsDF {

    public static ContactUsDO fillContactUsDetails(){
        ContactUsDO contactUs= new ContactUsDO();
        contactUs.setName("Amitesh");
        contactUs.setEmail("amitesh@yopmail.com");
        contactUs.setSubject("Amitesh");
        contactUs.setMessage("This is a message");
        return contactUs;
    }
}

