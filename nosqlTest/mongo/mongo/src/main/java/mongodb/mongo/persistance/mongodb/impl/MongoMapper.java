package mongodb.mongo.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import mongodb.mongo.dto.MongoDTO;
import mongodb.mongo.persistance.mongodb.AbstractMongoDBComon;
import mongodb.mongo.persistance.mongodb.IMongoMapper;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("MongoMapper")        //공통 기능 및 MongoDB 객체 사용을 위해 상속 받는다.
public class MongoMapper extends AbstractMongoDBComon implements IMongoMapper {

    @Override
    public int insertData(MongoDTO mongoDTO, String colNm) throws Exception {

        int res = 0;

        // 데이터를 저장할 컬렉션 생성, 부모 추상화 클래스의 컬렉션 생성 함수 호출
        super.createCollection(colNm);

        // 저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // DTO를 Map 데이터타입으로 변경 하고 변경된 Map 데이터 타입을 Document로 변경하기
        col.insertOne(new Document(new ObjectMapper().convertValue(mongoDTO, Map.class)));

        res = 1;


        return res;
    }
}
