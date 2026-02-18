package dataObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsDO {

    private String name;
    private String cardNumber;
    private String cvc;
    private String expiryMonth;
    private String expiryYear;
}


