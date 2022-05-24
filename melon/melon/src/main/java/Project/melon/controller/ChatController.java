package Project.melon.controller;

import Project.melon.dto.ChatDTO;
import Project.melon.service.IChatService;
import Project.melon.utill.CmmUtil;
import Project.melon.utill.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final IChatService iChatService;

    // 채팅방 초기 화면
    @GetMapping("/index")
    public String index() throws Exception {
        log.debug("### index Start : {}", this.getClass().getName());
        log.debug("### index End : {}", this.getClass().getName());
        return "chat/index";
    }

    // 채팅방 입장
    @PostMapping("/intro")
    public String intro(HttpServletRequest request, HttpSession session) throws Exception {

        log.debug("### intro Start : {}", this.getClass().getName());

        // 기존 세션에 저장된 값 삭제하기
        session.setAttribute("SS_ROOM_NAME", "");
        session.setAttribute("SS_USER_NAME", "");

        String room_name = CmmUtil.nvl(request.getParameter("room_name"));
        String userNm = CmmUtil.nvl(request.getParameter("user_name"));

        log.debug("### userNm : {}", userNm);
        log.debug("### room_name : {}", room_name);

        // 세션에 값 저장하기
        session.setAttribute("SS_ROOM_NAME", room_name);
        session.setAttribute("SS_USER_NAME", userNm);

        // 입장 환영 멘트 저장하기
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setRoomKey("Chat_" + room_name);
        chatDTO.setUserNm("관리자");
        chatDTO.setMsg(userNm + "님! [" + room_name + "] 채팅방 입장을 환영합니다.");
        chatDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));

        // 채팅 멘트 저장하기
        iChatService.insertChat(chatDTO);

        log.debug("### intro End : {}", this.getClass().getName());

        return "chat/intro";
    }

    // 채팅방 전체 리스트 가져오기
    @PostMapping("/roomList")
    @ResponseBody
    public Set<String> roomList() throws Exception {

        log.debug("### roomList Start : {}", this.getClass().getName());

        Set<String> rSet = iChatService.getRoomList();

        if (rSet == null) {
            rSet = new LinkedHashSet<String>();
        }

        log.debug("### roomList End : {}", this.getClass().getName());

        return rSet;
    }

    // 음성 대화 저장
    @PostMapping("/msg")
    @ResponseBody
    public List<ChatDTO> msg(HttpServletRequest request, HttpSession session) throws Exception {

        log.debug("### msg Start : {}", this.getClass().getName());

        String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));
        String userNm = CmmUtil.nvl((String) session.getAttribute("SS_USER_NAME"));

        String msg = CmmUtil.nvl(request.getParameter("send_msg"));

        log.debug("### userNM : {}", userNm);
        log.debug("### room_name : {}", room_name);
        log.debug("### msg : {}", msg);

        List<ChatDTO> chatDTOList = null;

        // 음성 메시지가 존재하는 경우에만 대화 가져오기
        if (msg.length() > 0) {

            ChatDTO chatDTO = new ChatDTO();
            chatDTO.setRoomKey("Chat_" + room_name);
            chatDTO.setUserNm(userNm);
            chatDTO.setMsg(msg);
            chatDTO.setDateTime(DateUtil.getDateTime("yyyy.MM.dd hh:mm:ss"));

            chatDTOList = iChatService.insertChat(chatDTO);

            if (chatDTOList == null) {
                chatDTOList = new LinkedList<>();
            }

            chatDTO = null;

        }

        log.debug("### msg End : {}", this.getClass().getName());

        return chatDTOList;
    }

    // 음성 대화 가져오기
    @PostMapping("/getMsg")
    @ResponseBody
    public List<ChatDTO> getMsg(HttpSession session) throws Exception{

        log.debug("### getMsg Start : {}", this.getClass().getName());

        String room_name = CmmUtil.nvl((String) session.getAttribute("SS_ROOM_NAME"));

        log.debug("$$$ room_name : {}", room_name);

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setRoomKey("Chat_" + room_name);

        List<ChatDTO> chatDTOList = iChatService.getChat(chatDTO);

        if (chatDTOList == null) {
            chatDTOList = new LinkedList<>();
        }

        chatDTO = null;

        log.debug("### getMsg End : {}", this.getClass().getName());

        return chatDTOList;
    }
}
