package mongodb.mongo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MongoDTO {

    String user_nm;
    String addr;
    String email;

    public MongoDTO(String user_nm, String addr, String email) {
        this.user_nm = user_nm;
        this.addr = addr;
        this.email = email;
    }
}
