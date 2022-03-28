package Project.melon.service.impl;

import Project.melon.dto.MongoDTO;
import Project.melon.persistance.mongodb.IMongoMapper;
import Project.melon.service.IMongoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("MongoService")
public class MongoService implements IMongoService {

    @Resource(name = "MongoMapper")
    private IMongoMapper mongoMapper;

    @Override
    public void mongoTest() throws Exception {

        String colNm = "MONGODB_TEST";
        MongoDTO mongoDTO = new MongoDTO();
        mongoDTO.setUser_nm("오주현");
        mongoDTO.setAddr("안양");
        mongoDTO.setEmail("ohju96@gmail.com");

        // MongoDB에 데이터 저장하기
        mongoMapper.insertData(mongoDTO, colNm);

    }
}
