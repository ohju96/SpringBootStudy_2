package Project.melon.redis;

import Project.melon.dto.ChatDTO;

import java.util.List;
import java.util.Set;

public interface IChatMapper {

    // 인터페이스 선언된 변수는 상수
    String roomInfoKey = "myRoomKey";

    // 채팅 룸 리스트 가져오기
    Set<String> getRoomList() throws Exception;

    // 채팅 대화 저장하기
    int insertChat(ChatDTO chatDTO) throws Exception;

    // 채팅 대화 가져오기
    List<ChatDTO> getChat(ChatDTO chatDTO) throws Exception;

    // 데이터 저장 유효시간을 시간 단위로 설정
    boolean setTimeOutHour(String roomKey, int hours) throws Exception;

    // 데이터 저장 유효시간을 분 단위로 설정
    boolean setTimeOutMinute(String roomKey, int minutes) throws Exception;
}
