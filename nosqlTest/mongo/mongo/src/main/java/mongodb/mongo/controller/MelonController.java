package mongodb.mongo.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mongodb.mongo.dto.MelonDTO;
import mongodb.mongo.service.IMelonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class MelonController {

    @Resource(name = "MelonService")
    private IMelonService iMelonService;

    /**
     * 멜론 노래 리스트 저장하기
     */
    @GetMapping(value = "melon/collectMelonSong")
    public String collectMelonRank() throws Exception {

        log.debug(this.getClass().getName() + ".collectMelonSong Start");

        // 수집 결과 출력
        String msg;

        int res = iMelonService.collectMelonSong();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.debug(this.getClass().getName() + ".collectionMelonSong End");

        return msg;
    }

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기
     */
    @GetMapping(value = "melon/getSongList")
    public List<MelonDTO> getSongList() throws Exception {

        log.debug(this.getClass().getName() + ".getSongList Start");

        List<MelonDTO> rList = iMelonService.getSongList();

        log.debug(this.getClass().getName() + ".getSongList End");

        return rList;
    }

    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @GetMapping(value = "melon/getSingerSongCnt")
    public List<MelonDTO> getSingerSongCnt() throws Exception {

        log.debug(this.getClass().getName() + ".getSingerSongCnt Start");

        List<MelonDTO> rList = iMelonService.getSingerSongCnt();

        log.debug(this.getClass().getName() + ".getSingerSongCnt");

        return rList;
    }


}
