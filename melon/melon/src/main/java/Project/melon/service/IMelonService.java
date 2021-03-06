package Project.melon.service;

import Project.melon.dto.MelonDTO;

import java.util.List;
import java.util.Map;

public interface IMelonService {

    /**
     * 멜론 노래 리스트 저장하기
     */
    int collectMelonSong() throws Exception;

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     */
    List<MelonDTO> getSongList() throws Exception;

    /**
     * 멜론 가수별 노래 수 가져오기
     */
    List<MelonDTO> getSingerSongCnt() throws Exception;

    /**
     * 가수의 노래 가져오기
     *
     * @return 노래 리스트
     */
    List<MelonDTO> getSingerSong() throws Exception;

    /**
     * 멜론 노래 리스트 한번에 저장하기
     */
    int collectMelonSongMany() throws Exception;

    /**
     * 가수의 노래 삭제하기
     * @return
     * @throws Exception
     */
    int deleteSong() throws Exception;

    /**
     * singer 필드의 값이 방탄소년단을 BTS로 변경하기
     * @return
     * @throws Exception
     */
    int updateBTSName() throws Exception;

    /**
     * BTS 노래마다 nickname 필드를 추가하고,
     * 그 필드에 BTS 저장하기
     * @return
     * @throws Exception
     */
    int updateAddBTSNickname() throws Exception;

    /**
     * BTS 노래에 member 필드를 추가하고,
     * 그 member 필드에 BTS 멤버 이름을 List로 저장한다.
     * @return
     * @throws Exception
     */
    int updateAddBTSMember() throws Exception;
}
