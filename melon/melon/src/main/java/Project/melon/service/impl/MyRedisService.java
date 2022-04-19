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
}
