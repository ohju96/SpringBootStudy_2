package mongodb.mongo.persistance.mongodb;

import mongodb.mongo.dto.MongoDTO;

public interface IMongoMapper {

    /**
     * 간단한 데이터 저장하기
     * @param mongoDTO 저장될 정보
     * @param colNm 저장할 컬렉션 이름
     * @return 저장 결과
     * @throws Exception
     */
    int insertData(MongoDTO mongoDTO, String colNm) throws Exception;
}
