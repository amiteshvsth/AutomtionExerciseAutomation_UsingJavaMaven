package API.dataObjects.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDO {

    private int id;
    private String name;
    private String email;
    private String title;
    private String birth_day;
    private String birth_month;
    private String birth_year;
    private String first_name;
    private String last_name;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;
}