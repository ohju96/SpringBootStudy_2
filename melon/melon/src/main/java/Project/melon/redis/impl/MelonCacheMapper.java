package Project.melon.redis.impl;

import Project.melon.dto.MelonDTO;
import Project.melon.redis.IMelonCacheMapper;
import Project.melon.utill.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("MelonCacheMapper")
@RequiredArgsConstructor
public class MelonCacheMapper implements IMelonCacheMapper {

    public final RedisTemplate<String, Object> redisDB;

    @Override
    public int insertSong(List<MelonDTO> pList, String redisKey) throws Exception {

        int res = 0;

        // Redis에 저장할 키
        String key = "MELON_" + DateUtil.getDateTime("yyyMMdd");

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MelonDTO.class));

        // 람다식으로 데이터 저장하기
        pList.forEach(melon -> redisDB.opsForList().leftPush(key, melon));

        // 저장된 데이터는 1시간 동안 보관
        redisDB.expire(key, 1, TimeUnit.HOURS);

        res = 1;

        return res;
   }

    @Override
    public boolean getExistKey(String redisKey) throws Exception {

        // 저장된 키가 존재한다면
        if (redisDB.hasKey(redisKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<MelonDTO> getSongList(String redisKey) throws Exception {

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(MelonDTO.class));

        List<MelonDTO> rList = null;

        // 저장된 키가 존재한다면
        if (redisDB.hasKey(redisKey)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);
        }
        return rList;
    }
}
