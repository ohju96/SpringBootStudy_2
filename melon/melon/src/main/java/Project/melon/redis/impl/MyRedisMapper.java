package Project.melon.redis.impl;

import Project.melon.dto.RedisDto;
import Project.melon.redis.IMyRedisMapper;
import Project.melon.utill.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper {

    public final RedisTemplate<String, Object> redisDB;

    public MyRedisMapper(RedisTemplate<String, Object> redisDB) {
        this.redisDB = redisDB;
    }

    @Override
    public int saveRedisString(String redisKey, RedisDto redisDto) throws Exception {

        log.info(this.getClass().getName() + ".saveRedisString Start");

        int res = 0;

        String saveData = redisDto.getTest_text();

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (!redisDB.hasKey(redisKey)) {

            redisDB.opsForValue().set(redisKey, saveData);

            redisDB.expire(redisKey, 2, TimeUnit.DAYS);

            log.info("Save Data!");
        }

        res = 1;

        log.info(this.getClass().getName() + ".saveRedisString ENd!");

        return res;
    }

    @Override
    public RedisDto getRedisString(String redisKey) throws Exception {

        RedisDto redisDto = new RedisDto();

        redisDB.setKeySerializer(new StringRedisSerializer()); //String 타입
        redisDB.setValueSerializer(new StringRedisSerializer()); // String 타입

        if (redisDB.hasKey(redisKey)) { // 데이터가 존재하지 않으면 저장하기
            String res = (String) redisDB.opsForValue().get(redisKey);
            redisDto.setTest_text(res);
        }


        return redisDto;
    }

    @Override
    public int saveRedisStringJSON(String redisKey, RedisDto redisDto) throws Exception {

        int res = 0;

        //redisDB의 키의 데이터 타입을 String으로 정의(항상 String으로 설정한다.)
        redisDB.setKeySerializer(new StringRedisSerializer()); //String 타입

        // REdisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDto.class));

        if (!redisDB.hasKey(redisKey)) {
            // 데이터 저장하기
            redisDB.opsForValue().set(redisKey, redisDto);

            //RedisDb에 저장되는 데이터의 유효시간 설정(TTL 설정)
            // 2일이 지나면 자동으로 데이터가 삭제되도록 설정한다.
            redisDB.expire(redisKey, 2, TimeUnit.DAYS);

            res = 1;
        }

        return res;
    }

    @Override
    public RedisDto getRedisStringJSON(String redisKey) throws Exception {

        RedisDto redisDto = null;

        // redisDB의 키 데이터 타입을 String으로 정의(항상 String으로 설정함)
        redisDB.setKeySerializer(new StringRedisSerializer()); //String 타입

        //RedisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDto.class));

        if (redisDB.hasKey(redisKey)) { //데이터가 존재하지 않으면 저장하기
            redisDto = (RedisDto) redisDB.opsForValue().get(redisKey);
        }

        return redisDto;
    }

    @Override
    public int saveRedisList(String redisKey, List<RedisDto> pList) throws Exception {

        int res = 0;

        // redis 저장 및 읽기에 대한 데이터 타입 지정(String 타입으로 지정함)
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new StringRedisSerializer());

        for (RedisDto dto : pList) {
            //오름차순 저장
            redisDB.opsForList().rightPush(redisKey, CmmUtil.nvl(dto.getTest_text()));
            //내림차순으로 저장
            //redisDB.opsForList().leftPush(redisKey, CmmUtil.nvl(dto.getTest_text()));
        }


        redisDB.expire(redisKey, 5, TimeUnit.HOURS);

        res = 1;

        return res;
    }

    @Override
    public List<String> getRedisList(String redisKey) throws Exception {

        List<String> rList = null;

        // redis 저장 및 일기에 대한 데이터 타입 지정(String 타입으로 지정함)
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new StringRedisSerializer());

        if (redisDB.hasKey(redisKey)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);
        }

        return rList;
    }

    @Override
    public int saveRedisListJSON(String redisKey, List<RedisDto> pList) throws Exception {

        int res = 0;

        // redisDB의 키의 데이터 타입을 String으로 정의 (항상 String으로 설정한다.)
        redisDB.setKeySerializer(new StringRedisSerializer()); // String 타입

        // redisDTO에 저장된 데이터를 자동으로 JSON으로 변경하기
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisDto.class));

        for (RedisDto redisDto : pList) {
            //오름차순으로 저장
            redisDB.opsForList().rightPushIfPresent(redisKey, redisDto);

            // 내림차순으로 저장하기
            //redisDB.opsForList().leftPush(redisKey, redisDto);

        }

        // 저장되는 데이터의 유효기간(TTL)은 5시간으로 정의
        redisDB.expire(redisKey, 5, TimeUnit.HOURS);

        res = 1;

        return res;
    }
}
