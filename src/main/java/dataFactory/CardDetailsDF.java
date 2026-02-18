package dataFactory;

import dataObject.CardDetailsDO;

public class CardDetailsDF {

    public static CardDetailsDO fillContactUsDetails(){
        CardDetailsDO cardDetails= new CardDetailsDO();
        cardDetails.setName("John Doe");
        cardDetails.setCardNumber("1234567890");
        cardDetails.setCvc("123");
        cardDetails.setExpiryMonth("12");
        cardDetails.setExpiryYear("1999");
        return cardDetails;
    }
}

