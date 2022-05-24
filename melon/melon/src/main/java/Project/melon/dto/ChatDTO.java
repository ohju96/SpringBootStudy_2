package Project.melon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ChatDTO {

    private String roomKey = "";
    private String userNm = "";
    private String msg = "";
    private String dateTime = "";

}
