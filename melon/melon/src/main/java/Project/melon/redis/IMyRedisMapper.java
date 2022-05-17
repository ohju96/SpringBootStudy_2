package Project.melon.redis;

import Project.melon.dto.RedisDto;

import java.util.List;
import java.util.Set;

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

    /**
     * List 타입에 JSON 형태로 저장하기(동기화)
     */
    int saveRedisListJSON(String redisKey, List<RedisDto> pList) throws Exception;

    /**
     * List 타입에 JSON 형태로 저장된 데이터 가져오기
     */
    List<RedisDto> getRedisListJSON(String redisKey) throws Exception;

    /**
     * List 타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
     */
    int saveRedisListJSONRamda(String redisKey, List<RedisDto> pList) throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    int saveRedisHash(String redisKey, RedisDto redisDto) throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장된 값 가져오기
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    RedisDto getRedisHash(String redisKey) throws Exception;

    /**
     * Set 타입에 JSON 형태로 람다식을 이용하여 저장하기
     * @param redisKey Redis 저장 키
     * @param pSet 저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisSetJSONRamda(String redisKey, Set<RedisDto> pSet) throws Exception;

    /**
     * Set 타입에 JSON 형태로 람다식을 이용하여 저장된 값 가져오기
     * @param redisKey 가져올 REdisKEy
     * @return 결과 값
     */
    Set<RedisDto> getRedisSetJSONRamda(String redisKey) throws Exception;

    /**
     *  ZSet 타입에 JSON 형태로 저장하기
     * @param redisKey Redis 저장 키
     * @param pList 저장할 정보들
     * @return 저장 성공 여부
     */
    int saveRedisZSetJSON(String redisKey, List<RedisDto> pList) throws Exception;

    /**
     * ZSet 타입에 JSON 형태로 저장된 값 가져오기
     * @param redisKey 가져올 RedisKey
     * @return 결과 값
     */
    Set<RedisDto> getRedisZSetJSON(String redisKey) throws Exception;

    /**
     * Redis에 JSON 구조로 저장된 데이터 삭제하기
     * @param redisKey 삭제할 RedisKey
     * @return 결과 값
     */
    boolean deleteDataJSON(String redisKey) throws Exception;
}
