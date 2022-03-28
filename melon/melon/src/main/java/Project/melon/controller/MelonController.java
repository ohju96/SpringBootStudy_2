package Project.melon.controller;

import Project.melon.dto.MelonDTO;
import Project.melon.service.IMelonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class MelonController {

    @Resource(name = "MelonService")
    private IMelonService melonService;

    /**
     * 멜론 노래 리스트 저장하기
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "melon/collectMelonSong")
    public String collectMelonRank() throws Exception {

        log.info(this.getClass().getName() + ".collectionMelonRank");

        // 수집 결과 출력
        String msg;

        int res = melonService.collectMelonSong();

        if (res == 1) {
            msg = "success";
        } else {
            msg = "fail";
        }

        log.info(this.getClass().getName() + ".collectMelonSong End !!");

        return msg;
    }

    /**
     * 오늘 수집된 멜론 노래리스트 가져오기기
     */
    @GetMapping("melon/getSongList")
    public List<MelonDTO> getSongList() throws Exception {

        log.info(this.getClass().getName() + ".getSongList Start !!");

        List<MelonDTO> rList = melonService.getSongList();

        log.info(this.getClass().getName() + ".getSongList End !!");

        return rList;
    }

    /**
     * 가수별 수집된 노래의 수 가져오기
     */
    @GetMapping(value = "melon/getSingerSongCnt")
    public List<Map<String,Object>> getSingerSongCnt() throws Exception {

        log.info(this.getClass().getName() + ".getSingerSongCnt Start !!");

        List<Map<String, Object>> rList = melonService.getSingerSongCnt();

        log.info(this.getClass().getName() + "getSingerSongCnt End !");

        return rList;
    }



}
