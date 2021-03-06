package Project.melon.service.impl;

import Project.melon.dto.RedisDto;
import Project.melon.redis.IMyRedisMapper;
import Project.melon.service.IMyRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service("MyRedisService")
public class MyRedisService implements IMyRedisService {

    @Resource(name = "MyRedisMapper")
    private IMyRedisMapper iMyRedisMapper;

    @Override
    public int saveRedisString() throws Exception {

        String redisKey = "myRedis_String";

        RedisDto redisDto = new RedisDto();
        redisDto.setTest_text("난 String으로 저장할 일반 문자열이다.");

        int res = iMyRedisMapper.saveRedisString(redisKey, redisDto);

        return res;
    }

    @Override
    public RedisDto getRedisString() throws Exception {

        String redisKey = "myRedis_String";

        RedisDto redisDto = iMyRedisMapper.getRedisString(redisKey);

        if (redisDto == null) {
            redisDto = new RedisDto();
        }


        return redisDto;
    }

    @Override
    public int saveREdisStringJSON() throws Exception {

        String redisKey = "myRedis_String_JSON";

        RedisDto redisDto = new RedisDto();
        redisDto.setTest_text("난 String 타입에 JSON 구조로 저장할 일반 문자열이다.");
        redisDto.setName("오주현");
        redisDto.setAddr("경기");
        redisDto.setEmail("ohju96@gmail.com");

        int res = iMyRedisMapper.saveRedisStringJSON(redisKey, redisDto);

        return res;
    }

    @Override
    public RedisDto getRedisStringJSON() throws Exception {
        String redisKey = "myRedis_String_JSON";

        RedisDto redisDto = iMyRedisMapper.getRedisStringJSON(redisKey);

        if (redisDto == null) {
            redisDto = new RedisDto();
        }

        return redisDto;
    }

    @Override
    public int saveRedisList() throws Exception {

        String redisKey = "myRedis_List";

        LinkedList<RedisDto> pList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            RedisDto redisDto = new RedisDto();
            redisDto.setTest_text(i + "번째 데이터입니다.");
            pList.add(redisDto);
            redisDto = null;
        }
        int res = iMyRedisMapper.saveRedisList(redisKey, pList);

        return res;
    }

    @Override
    public List<String> getRedisList() throws Exception {

        String redisKey = "myRedis_List";

        List<String> rList = iMyRedisMapper.getRedisList(redisKey);

        if (rList == null) {
            rList = new LinkedList<>();
        }

        return rList;
    }

    @Override
    public int saveRedisListJSON() throws Exception {

        String redisKey = "myRedis_List_JSON";

        List<RedisDto> pList = new LinkedList<>();

        for (int i = 0; i < 10; i ++) {
            RedisDto redisDto = new RedisDto();
            redisDto.setTest_text(i + "번째 데이터입니다.");
            redisDto.setName("오주현[" + i + "]");
            redisDto.setAddr("경기");
            redisDto.setEmail("ojh@ojh");

            pList.add(redisDto);
            redisDto = null;
        }

        int res = iMyRedisMapper.saveRedisListJSON(redisKey, pList);

        return res;
    }

    @Override
    public List<RedisDto> getRedisListJSON() throws Exception {
        String redisKey = "myRedis_List_JSON";

        List<RedisDto> rList = iMyRedisMapper.getRedisListJSON(redisKey);

        if (rList == null) {
            rList = new LinkedList<>();
        }

        return rList;
    }

    @Override
    public int saveRedisListJSONRamda() throws Exception {

        String redisKey = "myRedis_List_JSON_Ramda";

        List<RedisDto> pList = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            RedisDto redisDto = new RedisDto();
            redisDto.setTest_text(i + "번째 데이터입니다.");
            redisDto.setName("오주현[" + i + "]");
            redisDto.setAddr("경기");
            redisDto.setEmail("ojh@ojh");

            pList.add(redisDto);
            redisDto = null;
        }

        int res = iMyRedisMapper.saveRedisListJSONRamda(redisKey, pList);

        return res;
    }

    @Override
    public List<RedisDto> getRedisListJSONRamda() throws Exception {

        String redisKey = "myRedis_List_JSON_Ramda";

        List<RedisDto> rList = iMyRedisMapper.getRedisListJSON(redisKey);

        if (rList == null) {
            rList = new LinkedList<>();
        }

        return rList;
    }

    @Override
    public int saveRedisHash() throws Exception {

        String redisKey = "myRedis_Hash";

        RedisDto redisDto = new RedisDto();
        redisDto.setName("오주현");
        redisDto.setAddr("경기");
        redisDto.setEmail("ojh@ojh");

        int res = iMyRedisMapper.saveRedisHash(redisKey, redisDto);

        return res;
    }

    @Override
    public RedisDto getRedisHash() throws Exception {

        String redisKey = "myReids_Hash";

        RedisDto redisDto = iMyRedisMapper.getRedisHash(redisKey);

        if (redisDto == null) {
            redisDto = new RedisDto();
        }

        return redisDto;
    }

    @Override
    public int saveREdisSetJSONRamda() throws Exception {

        String redisKey = "myRedis_Set_JSON";

        Set<RedisDto> pSet = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            RedisDto redisDto = new RedisDto();
            redisDto.setTest_text(i + "번째 데이터입니다.");
            redisDto.setName("오주현[" + i + "]");
            redisDto.setAddr("경기");
            redisDto.setEmail("ojh@ojh");

            pSet.add(redisDto);
            redisDto = null;
        }

        int res = iMyRedisMapper.saveRedisSetJSONRamda(redisKey, pSet);

        return res;
    }

    @Override
    public Set<RedisDto> getRedisSetJSONRamda() throws Exception {

        String redisKey = "myRedis_Set_JSON";
        Set<RedisDto> rSet = iMyRedisMapper.getRedisSetJSONRamda(redisKey);

        if (rSet == null) {
            rSet = new HashSet<>();
        }

        return rSet;
    }

    @Override
    public int saveRedisZSetJSON() throws Exception {

        String redisKey = "myRedis_Zset_JSON";

        List<RedisDto> pList = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            RedisDto redisDto = new RedisDto();
            redisDto.setTest_text(i + " 번째 데이터입니다.");
            redisDto.setName("오주현[" + i + "]");
            redisDto.setAddr("경기");
            redisDto.setEmail("qwe@naver.com");

            pList.add(redisDto);
            redisDto = null;
        }

        log.debug("### pList : {}", pList);

        int res = iMyRedisMapper.saveRedisZSetJSON(redisKey, pList);

        return res;
    }

    @Override
    public Set<RedisDto> getRedisZSetJSON() throws Exception {

        String redisKey = "myRedis_Zset_JSON";

        Set<RedisDto> redisDtoSet = iMyRedisMapper.getRedisZSetJSON(redisKey);

        if (redisDtoSet == null) {
            redisDtoSet = new HashSet<>();
        }

        return redisDtoSet;
    }

    @Override
    public boolean deleteDataJSON() throws Exception {

        String redisKey = "myRedis_Zset_JSON";

        boolean res = iMyRedisMapper.deleteDataJSON(redisKey);

        return res;
    }
}
