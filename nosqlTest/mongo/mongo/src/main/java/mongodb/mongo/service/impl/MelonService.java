package mongodb.mongo.service.impl;

import lombok.extern.slf4j.Slf4j;
import mongodb.mongo.dto.MelonDTO;
import mongodb.mongo.persistance.mongodb.IMelonMapper;
import mongodb.mongo.service.IMelonService;
import mongodb.mongo.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service("MelonService")
public class MelonService implements IMelonService {

    @Resource(name = "MelonMapper")
    private IMelonMapper iMelonMapper; //MongoDB에 저장할 Mapper

    @Override
    public int collectMelonSong() throws Exception {

        log.debug(this.getClass().getName() + ".collectMelonRank Start");

        int res = 0;

        List<MelonDTO> pList = new LinkedList<>();

        //멜론 Top 100 중 50위까지 정보 가져오는 페이지
        String url = "https://www.melon.com/chart/index.htm";

        //JSOUP 라이브러리를 통해 사이트에 접속되면 그 사이트의 전체 HTML소스를 저장할 변수
        Document doc = Jsoup.connect(url).get();

        // <div class="service_list_song"> 이 태그 내에 있는 HTML 소스만 element에 저장된다.
        Elements element = doc.select("div.service_list_song");

        for (Element songInfo : element.select("div.wrap_song_info")) {

            // 크롤링을 통해 데이터 저장하기
            String song = songInfo.select("div.ellipsis.rank01 a").text(); //노래
            String singer = songInfo.select("div.ellipsis.rank02 a").eq(0).text(); //가수

            log.debug("song : {}", song);
            log.debug("singer : {}", singer);

            if ((song.length() > 0) && (singer.length() > 0)) {

                MelonDTO melonDTO = new MelonDTO();
                melonDTO.setCollectTime(DateUtil.getDateTime("yyyyMMddhhmmss"));
                melonDTO.setSong(song);
                melonDTO.setSinger(singer);

                //한 번에 여러 개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
                pList.add(melonDTO);
            }

        }

        //생성할 컬렉션명
        String cloNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        //MongoDB에 데이터저장하기
        res = iMelonMapper.insertSong(pList, cloNm);

        log.debug(this.getClass().getName() + ".collectionMelonSong End");

        return res;
    }


    @Override
    public List<MelonDTO> getSongList() throws Exception {

        log.debug(this.getClass().getName() + ".getSongList Start");

        // MongoDB에 저장될 컬렉션 이름
        String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        List<MelonDTO> rList = new LinkedList<>();

        rList = iMelonMapper.getSongList(colNm);

        if (rList == null) {
            rList = new LinkedList<>();
        }

        log.debug(this.getClass().getName() + ".getSongList ENd");

        return rList;
    }

    @Override
    public List<MelonDTO> getSingerSongCnt() throws Exception {

        log.debug(this.getClass().getName() + ".getSingerSongCNt");

        String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");
        List<MelonDTO> rList = iMelonMapper.getSingerSongCnt(colNm);

        if (rList == null) {
            rList = new LinkedList<>();
        }

        log.debug(this.getClass().getName() + ".getSingerSongCnt End");

        return rList;
    }

    @Override
    public List<MelonDTO> getSingerSong() throws Exception {

        log.debug(this.getClass().getName() + "getSingerSong");

        //MongoDB에 저장된 컬렉션 이름
        String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

        // 수집된 데이터로부터 검색할 가수명
        String singer = "방탄소년단";

        // 결과값
        List<MelonDTO> rList = null;

        // Melon 노래 수집
        if (this.collectMelonSong() == 1) {
            rList = iMelonMapper.getSingerSong(colNm, singer);

            if (rList == null) {
                rList = new LinkedList<>();
            }

        } else {
            rList = new LinkedList<>();
        }

        log.debug(this.getClass().getName() + ".getSingerSong End");

        return rList;
    }
}
