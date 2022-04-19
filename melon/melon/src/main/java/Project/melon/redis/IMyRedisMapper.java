package Project.melon.redis;

import Project.melon.dto.RedisDto;

public interface IMyRedisMapper {

    int saveRedisString(String redisKey, RedisDto redisDto) throws Exception;
}
