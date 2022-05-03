package Project.melon.controller;

import Project.melon.dto.RedisDto;
import Project.melon.service.IMyRedisService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

}
