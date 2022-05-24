package Project.melon.service.impl;

import Project.melon.dto.ChatDTO;
import Project.melon.redis.IChatMapper;
import Project.melon.service.IChatService;
import Project.melon.utill.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service("ChatService")
@RequiredArgsConstructor
public class ChatService implements IChatService {

    private final IChatMapper iChatMapper;

    @Override
    public Set<String> getRoomList() throws Exception {
        log.debug("### getRoomList Start : {}", this.getClass().getName());
        return iChatMapper.getRoomList();
    }

    @Override
    public List<ChatDTO> insertChat(ChatDTO chatDTO) throws Exception {

        log.debug("### insertChat Start : {}", this.getClass().getName());

        //채팅 내용 저장
        if (iChatMapper.insertChat(chatDTO) == 1) {
            log.debug("### Success");
            // 데이터 만료시간 정의(채팅 이후 5분까지만 데이터 저장
            iChatMapper.setTimeOutHour(CmmUtil.nvl(chatDTO.getRoomKey()), 5);
        } else {
            log.debug("### Fail");
        }

        log.debug("### insertChat End : {}", this.getClass().getName());

        //채팅 내용 가져오기
        return getChat(chatDTO);
    }

    @Override
    public List<ChatDTO> getChat(ChatDTO chatDTO) throws Exception {

        log.debug("### getChat Start : {}", this.getClass().getName());

        return iChatMapper.getChat(chatDTO);
    }

    @Override
    public boolean setTimeOutHour(String roomKey, int hours) throws Exception {
        return false;
    }

    @Override
    public boolean setTimeOutMinute(String roomKey, int minutes) throws Exception {
        return false;
    }
}
