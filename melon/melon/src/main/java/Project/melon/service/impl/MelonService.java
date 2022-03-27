package Project.melon.service.impl;

import Project.melon.dto.MelonDTO;
import Project.melon.persistance.mongodb.impl.IMelonMapper;
import Project.melon.service.IMelonService;
import Project.melon.utill.CmmUtil;
import Project.melon.utill.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("MelonService")
public class MelonService implements IMelonService {

    @Resource(name = "MelonMapper")
    private IMelonMapper melonMapper; //MongoDB에 저장할 Mapper

    @Override
    public int collectMelonSong() throws Exception {

        log.info(this.getClass().getName() + ".collectMelonRank Start !!");

        int res = 0;

        LinkedList<MelonDTO> pList = new LinkedList<>();

        // 멜론 Top 100 중 50위까지 정보 가져오는 페이지
        String url = "https://www.melon.com/chart/index.htm";

        // JSOUP 라이브러리를 통해 사이틍에 접속이 되면 그 사이트의 전체 HTML 소스를 저장할 변수
        Document doc = Jsoup.connect(url).get();

        // <div class="service_list_song"> 이 태그 내에서 있는 HTML 소스만 element에 저장이 된다.
        Elements elements = doc.select("div.service_list_song");

        for (Element songInfo : elements.select("div.warp_song_info")) {

            // 크롤링을 통해 데이터 저장하기
            String song = CmmUtil.nvl(songInfo.select("div.ellipsis.rank01 a").text()); //노래
            String singer = CmmUtil.nvl(songInfo.select("div.ellipsis.rank02 a").eq(0).text()); //가수

            log.info("song ={}", song);
            log.info("finger ={}", singer);

            //가수와 노래 정보가 모두 수집되면 저장
            if ((song.length() > 0) && (singer.length() > 0)) {

                MelonDTO pDTO = new MelonDTO();
                pDTO.setCollectTime(DateUtil.getDateTime("yyyyMMddhhmmss"));
                pDTO.setSong(song);
                pDTO.setSinger(singer);

                // 한번에 여러개의 데이터를 MongoDB에 저장할 List 형태의 데이터 저장하기
                pList.add(pDTO);

                // 생성할 컬렉션명
                String colNm = "MELON_" + DateUtil.getDateTime("yyyyMMdd");

                // MongoDB에 데이터저장하기
                res = melonMapper.insertSong(pList, colNm);

                log.info(this.getClass().getName() + ".collectMelonSong End !");

                return res;

            }


        }
        return 0;
    }

    @Override
    public List<MelonDTO> getSongList() throws Exception {
        return null;
    }

    @Override
    public List<Map<String, Object>> getSingerSongCnt() throws Exception {
        return null;
    }
}
