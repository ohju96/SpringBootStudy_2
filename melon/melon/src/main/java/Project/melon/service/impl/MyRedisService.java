package Project.melon.service.impl;

import Project.melon.dto.RedisDto;
import Project.melon.redis.IMyRedisMapper;
import Project.melon.service.IMyRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
