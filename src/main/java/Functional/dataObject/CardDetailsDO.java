package Functional.dataObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsDO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("cardNumber")
    private String cardNumber;

    @JsonProperty("cvc")
    private String cvc;

    @JsonProperty("expiryMonth")
    private String expiryMonth;

    @JsonProperty("expiryYear")
    private String expiryYear;
}