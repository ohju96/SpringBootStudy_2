package Project.melon.service;

import Project.melon.dto.RedisDto;

public interface IMyRedisService {
    int saveRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    RedisDto getRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    int getRedisStringJSON() throws Exception;


}
