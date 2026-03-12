package Functional.dataFactory;

import Functional.dataObject.CardDetailsDO;

public class CardDetailsDF extends BaseDF {

    public static CardDetailsDO fillContactUsDetails(){
        CardDetailsDO cardDetails = new CardDetailsDO();

        cardDetails.setName(faker.name().fullName());
        cardDetails.setCardNumber(faker.finance().creditCard().replaceAll("-", ""));
        cardDetails.setCvc(String.valueOf(faker.number().numberBetween(100,999)));
        cardDetails.setExpiryMonth(String.valueOf(faker.number().numberBetween(1,12)));
        cardDetails.setExpiryYear(String.valueOf(faker.number().numberBetween(2026,2032)));
        return cardDetails;
    }
}

