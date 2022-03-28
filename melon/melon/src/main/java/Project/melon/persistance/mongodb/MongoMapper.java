package Project.melon.persistance.mongodb;

import Project.melon.dto.MongoDTO;
import Project.melon.persistance.mongodb.impl.IMongoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("MongoMapper")
public class MongoMapper extends AbstractMongoDBComon implements IMongoMapper {

    @Override
    public int insertData(MongoDTO pDTO, String colNm) throws Exception {

        log.info(this.getClass().getName() + ".insertData Start!");

        int res = 0;

        // 데이터를 저장할 컬렉션 생성
        super.createCollection(colNm);

        // 저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경하고 변경된 Map 데이터를 Document로 변경한다.
        col.insertOne(new Document(new ObjectMapper().convertValue(pDTO, Map.class)));

        res = 1;

        log.info(this.getClass().getName() + ".insertData End !!");

        return res;
    }
}
