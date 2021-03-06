package mongodb.mongo.persistance.mongodb;

import mongodb.mongo.dto.MelonDTO;

import java.util.List;

public interface IMelonMapper {

    /**
     * 멜론 노래 리스트 저장하기
     * @param pList 저장될 정보
     * @param cloNm 저장할 컬렉션 이름
     * @return 저장 결과
     */
    int insertSong(List<MelonDTO> pList, String cloNm) throws Exception;

    /**
     * 오늘 수집된 멜론 노래 리스트 가져오기
     * @param colNm 조회할 컬렉션 이름
     * @return 노래 리스트
     */
    List<MelonDTO> getSongList(String colNm) throws Exception;

    /**
     * 가수별 수집된 노래의 수 가져오기
     * @param colNm 조회할 컬렉션 이름
     * @return 노래 리스트
     */
    List<MelonDTO> getSingerSongCnt(String colNm) throws Exception;

    /**
     * 가수의 노래 가져오기
     * @param colNm 조회할 컬렉션 이름
     * @param singer 가수명
     * @throws Exception
     */
    List<MelonDTO> getSingerSong(String colNm, String singer) throws Exception;

    /**
     * 멜론 노래 리스트 저장하기
     * @param melonDTO
     * @param colNm
     */
    int insertSongMany(List<MelonDTO> melonDTO, String colNm) throws Exception;

    /**
     * 컬렉션 삭제하기
     * @param colNm 삭제할 컬렉션 이름
     * @return 저장 결과
     */
    int dropMelonCollertion(String colNm) throws Exception;
}


