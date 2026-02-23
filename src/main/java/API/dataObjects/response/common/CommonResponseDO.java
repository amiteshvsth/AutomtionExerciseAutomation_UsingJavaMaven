package API.dataObjects.response.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseDO {

    private int responseCode;
    private String message;
}
