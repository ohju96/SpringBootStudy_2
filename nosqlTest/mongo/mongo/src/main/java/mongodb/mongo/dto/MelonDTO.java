package mongodb.mongo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class MelonDTO {

    String collectTime;
    String song;
    String singer;
    int singerCnt;

}
