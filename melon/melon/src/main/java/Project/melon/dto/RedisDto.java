package Project.melon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class RedisDto {

    private String name = "";
    private String email = "";
    private String addr = "";
    private String test_text = "";

}
