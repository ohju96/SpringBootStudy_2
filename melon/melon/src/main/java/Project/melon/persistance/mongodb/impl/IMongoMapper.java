package Project.melon.persistance.mongodb.impl;

import Project.melon.dto.MongoDTO;

public interface IMongoMapper {

    /**
     * 간단한 데이터 저장하기
     * @param pDTO
     * @param colNm
     * @return
     * @throws Exception
     */
    int insertData(MongoDTO pDTO, String colNm) throws Exception;
}
