package Project.melon.redis.impl;

import Project.melon.dto.ChatDTO;
import Project.melon.redis.IChatMapper;
import Project.melon.utill.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component("ChatMapper")
@RequiredArgsConstructor
public class ChatMapper implements IChatMapper {

    private final RedisTemplate<String, Object> redisDB;

    @Override
    public Set<String> getRoomList() throws Exception {

        log.debug("### getRoomList Start : {}", this.getClass().getName());

        //이름이 Chat으로 시작하는 Key만 가져오기
        Set<String> rSet = (Set) redisDB.keys("Chat*");

        log.debug("### getRoomList End : {}", this.getClass().getName());

        return rSet;
    }

    @Override
    public int insertChat(ChatDTO chatDTO) throws Exception {

        log.debug("### insertChat Start : {}", this.getClass().getName());

        int res = 0;

        // 대화방 키 정보
        String roomKey = CmmUtil.nvl(chatDTO.getRoomKey());

        redisDB.setKeySerializer(new StringRedisSerializer());//String 타입 정의
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class)); //Json 타입
        redisDB.opsForList().leftPush(roomKey, chatDTO); //저장하기

        res = 1;

        log.debug("### insertChat End : {}", this.getClass().getName());

        return res;
    }

    @Override
    public List<ChatDTO> getChat(ChatDTO chatDTO) throws Exception {

        log.debug("### getChat Start : {}", this.getClass().getName());

        // Redis에서 가져온 결과 저장할 객체
        List<ChatDTO> chatDTOList = null;

        // 대화방 키 정보
        String roomKey = CmmUtil.nvl(chatDTO.getRoomKey());

        // 데이터 타입 정의
        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatDTO.class));

        // 전체 데이터 조회하기
        if (redisDB.hasKey(roomKey)) {
            // 저장된 전체 레코드 가져오기
            chatDTOList = (List) redisDB.opsForList().range(roomKey, 0, -1);
        }

        log.debug("### getChat End : {}", this.getClass().getName());

        return chatDTOList;
    }

    @Override
    public boolean setTimeOutHour(String roomKey, int hours) throws Exception {
        log.debug("### setTimeOutHour Start : {}", this.getClass().getName());
        //데이터 유효시간(TTL) 분단위로 시간 설정하는 함수
        return redisDB.expire(roomKey, hours, TimeUnit.HOURS);
    }

    @Override
    public boolean setTimeOutMinute(String roomKey, int minutes) throws Exception {
        log.debug("### setTimeOutMinute Start : {}", this.getClass().getName());
        //데이터 유효시간(TTL) 분단위로 시간 설정하는 함수
        return redisDB.expire(roomKey, minutes, TimeUnit.MINUTES);
    }
}
