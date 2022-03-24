package Project.melon.persistance.mongodb.impl;

import Project.melon.dto.MelonDTO;

import java.util.List;
import java.util.Map;

public interface IMelonMapper {

    /**
     * 멜론 노래 리스트 저장
     * @param pList
     * @param colNm
     * @return
     * @throws Exception
     */
    int insertSong(List<MelonDTO> pList, String colNm) throws Exception;

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     * @param colNm
     * @return
     * @throws Exception
     */
    List<MelonDTO> getSongList(String colNm) throws Exception;

    /**
     * 가수별 수집된 노래의 수 가져오기
     * @param colNm
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> getSingerSongCnt(String colNm) throws Exception;
}
