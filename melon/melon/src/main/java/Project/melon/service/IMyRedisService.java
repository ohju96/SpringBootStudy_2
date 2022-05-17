package Project.melon.service;

import Project.melon.dto.RedisDto;

import java.util.List;
import java.util.Set;

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

    /**
     * List 타입에 JSON 형태로 저장하기(동기화)
     */
    int saveRedisListJSON() throws Exception;

    /**
     * List 타입에 JSNO 형태로 저장된 데이터 가져오기
     */
    List<RedisDto> getRedisListJSON() throws Exception;

    /**
     * List 타입에 JSON 형태로 람다식을 이용하여 저장하기(비동기화)
     */
    int saveRedisListJSONRamda() throws Exception;

    /**
     * List 타입에 JSON 형태로 저장된 데이터 가져오기
     *
     * 람다식 저장된 Redis 키 값이 달라서 함수 별도로 만듬
     * 매퍼 호출은 앞서 만든 getRedisListJSON 호출함
     */
    List<RedisDto> getRedisListJSONRamda() throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장하기
     */
    int saveRedisHash() throws Exception;

    /**
     * Hash 타입에 문자열 형태로 저장된 값 가져오기
     */
    RedisDto getRedisHash() throws Exception;

    /**
     * Set 타입에 JSON 형태로 람다식을 이용하여 저장하기

     */
    int saveREdisSetJSONRamda() throws Exception;

    /**
     * Set 타입에 JSON 형태로 람다식을 이용하여 저장된 값 가져오기
     */
    Set<RedisDto> getRedisSetJSONRamda() throws Exception;


    /**
     * ZSet 타입에 JSON 형태로 저장하기
     */
    int saveRedisZSetJSON() throws Exception;

    /**
     * ZSet 타입에 JSON 형태로 저장된 값 가져오기
     */
    Set<RedisDto> getRedisZSetJSON() throws Exception;

    /**
     * Redis에 JSON 구조로 저장된 데이터 삭제하기
     */
    boolean deleteDataJSON() throws Exception;

}
