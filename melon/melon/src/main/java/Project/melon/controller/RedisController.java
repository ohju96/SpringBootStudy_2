package Project.melon.controller;

import Project.melon.dto.RedisDto;
import Project.melon.service.IMyRedisService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
public class RedisController {

    @Resource(name = "MyRedisService")
    private IMyRedisService iMyRedisService;

    @GetMapping(value = "redis/saveRedisString")
    public String saveRedisString() throws Exception {
        String msg;

        int res = iMyRedisService.saveRedisString();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    /**
     * Redis 문자열 저장된 값 가져오기
     */
    @GetMapping(value = "redis/getRedisString")
    public RedisDto getRedisString() throws Exception {

        RedisDto redisDto = iMyRedisService.getRedisString();

        return redisDto;
    }

    @GetMapping(value = "redis/saveRedisStringJSON")
    public String saveRedisStringJSON() throws Exception {

        String msg;

        int res = iMyRedisService.saveREdisStringJSON();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    @GetMapping(value = "redis/getRedisStringJSON")
    public RedisDto getRedisStringJSON() throws Exception {
        RedisDto redisDto = iMyRedisService.getRedisStringJSON();
        return redisDto;
    }

    /**
     * List 타입에 여러 문자열로 저장하기(동기화)
     */
    @GetMapping(value = "redis/saveRedisList")
    public String saveRedisList() throws Exception {

        String msg;

        int res = iMyRedisService.saveRedisList();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    @GetMapping(value = "redis/getRedisList")
    private List<String> getRedisList() throws Exception {

        List<String> rList = iMyRedisService.getRedisList();

        return rList;
    }

    /**
     * List 타입에 JSON 형태로 저장하기(동기화)
     */
    @GetMapping(value = "redis/saveRedisListJSON")
    public String saveRedisListJSON() throws Exception {

        // 수집 결과 출력
        String msg;

        int res = iMyRedisService.saveRedisListJSON();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    /**
     * List 타입에 JSON 형태로 저장된 데이터 가져오기
     */
    @GetMapping(value = "redis/getRedisListJSON")
    public List<RedisDto> getRedisListJSON() throws Exception {
        List<RedisDto> rList = iMyRedisService.getRedisListJSON();
        return rList;
    }

    /**
     * List 타입에 JSNO 형태로 람다식을 이용하여 저장하기(비동기화)
     */
    @GetMapping(value = "redis/saveRedisListJSONRamda")
    public String saveRedisListJSONRamda() throws Exception {

        // 수집 결과 출력
        String msg;

        int res = iMyRedisService.saveRedisListJSONRamda();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    /**
     * List 타입에 JSON 형태로 저장된 데이터 가져오기
     */
    @GetMapping(value = "redis/getRedisListJSONRamda")
    public List<RedisDto> getRedisListJSONRamda() throws Exception {

        List<RedisDto> rList = iMyRedisService.getRedisListJSONRamda();

        return rList;
    }

    @GetMapping(value = "redis/saveRedisHash")
    public String saveRedisHash() throws Exception{

        // 수집 결과 출력
        String msg;

        int res = iMyRedisService.saveRedisHash();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    /**
     * Hash 타입에 문장려 형태로 저장된 값 가져오기
     */
    @GetMapping(value = "redis/getRedisHash")
    public RedisDto getRedisHash() throws Exception {

        RedisDto redisDto = iMyRedisService.getRedisHash();

        return redisDto;
    }

    @GetMapping(value = "redis/saveRedisSetJSONRamda")
    public String saveRedisSetJSONRamda() throws Exception {
        // 수집 결과 출력
        String msg;

        int res = iMyRedisService.saveREdisSetJSONRamda();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        return msg;
    }

    @GetMapping(value = "redis/getRedisSetJSONRamda")
    public Set<RedisDto> getRedisSetJSONRamda() throws Exception{

        Set<RedisDto> rSet = iMyRedisService.getRedisSetJSONRamda();

        return rSet;
    }
}
