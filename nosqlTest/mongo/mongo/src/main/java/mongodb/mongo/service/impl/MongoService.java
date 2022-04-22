package mongodb.mongo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mongodb.mongo.dto.MongoDTO;
import mongodb.mongo.persistance.mongodb.IMongoMapper;
import mongodb.mongo.service.IMongoService;
import org.springframework.stereotype.Service;

@Slf4j
@Service("MongoService")
@RequiredArgsConstructor
public class MongoService implements IMongoService {

    // MongoDB에 저장할 Mapper
    private final IMongoMapper iMongoMapper;

    @Override
    public void mongoTest() throws Exception {

        // 생성할 컬렉션 명
        String colNm = "MONGODB_TEST";

        MongoDTO mongoDTO = new MongoDTO("오주현", "경기도", "ohju96@gmail.com");

        iMongoMapper.insertData(mongoDTO, colNm);
    }
}
