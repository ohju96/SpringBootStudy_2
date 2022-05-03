package Project.melon.redis;

import Project.melon.dto.RedisDto;

import java.util.List;

public interface IMyRedisMapper {

    /**
     * String 타입 저장하기
     */
    int saveRedisString(String redisKey, RedisDto redisDto) throws Exception;

    /**
     * String 타입 가져오기
     */
    RedisDto getRedisString(String redisKey) throws Exception;

    /**
     *  String 타입에 JSON 형태로 저장한다.
     */
    int saveRedisStringJSON(String redisKey, RedisDto redisDto) throws Exception;

    /**
     * String 타입에 JSON 형태로 저장된 데이터 가져오기
     */
    RedisDto getRedisStringJSON(String redisKey) throws Exception;

    /**
     * List 타입에 여러 문자열로 저장하기(동기화)
     */
    int saveRedisList(String redisKey, List<RedisDto> pList) throws Exception;

    List<String> getRedisList(String redisKey) throws  Exception;
}
