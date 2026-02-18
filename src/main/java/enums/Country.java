package enums;

import lombok.Getter;

@Getter
public enum Country {

    INDIA("India"),
    USA("United States"),
    CANADA("Canada"),
    AUSTRALIA("Australia"),
    ISRAEL("Israel"),
    NEW_ZEALAND("New Zealand"),
    SINGAPORE("Singapore");

    public final String value;

    Country(String value) {
        this.value = value;
    }

}

