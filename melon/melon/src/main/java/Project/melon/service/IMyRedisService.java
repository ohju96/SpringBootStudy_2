package Project.melon.service;

import Project.melon.dto.RedisDto;

import java.util.List;

public interface IMyRedisService {
    int saveRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    RedisDto getRedisString() throws Exception;

    /**
     * String 타입 가져오기
     */
    int saveREdisStringJSON() throws Exception;

    RedisDto getRedisStringJSON() throws Exception;

    /**
     * List 타입에 여러 문자열로 저장하기(동기화)
     */
    int saveRedisList() throws Exception;

    List<String> getRedisList() throws Exception;
}
