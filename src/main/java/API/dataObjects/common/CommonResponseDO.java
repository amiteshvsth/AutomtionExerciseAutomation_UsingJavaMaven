package API.dataObjects.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseDO {

    @JsonProperty("responseCode")
    private int responseCode;
    @JsonProperty("message")
    private String message;
}
