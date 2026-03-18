package Functional.enums;

import lombok.Getter;

@Getter
public enum TopNavLinks {

    PRODUCTS("products"),
    LOGIN_PAGE("login"),
    LOGOUT_USER("logout"),
    DELETE_ACCOUNT("delete_account"),
    TEST_CASES_PAGE("test_cases"),
    API_LIST_PAGE("api_list"),
    CART_MENU_PAGE("view_cart");

    public final String value;

    TopNavLinks(String value) {
        this.value = value;
    }

}

