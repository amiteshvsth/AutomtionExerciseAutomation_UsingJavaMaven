package dataObject;
import enums.Country;
import enums.Title;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDO {

    private Title title;
    private String name;
    private String password;

    private String day;
    private String month;
    private String year;

    private boolean newsletter;
    private boolean option;

    private String firstName;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private Country country;
    private String state;
    private String city;
    private String zipcode;
    private String mobile;



}
