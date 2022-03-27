package Project.melon.service.impl;

import Project.melon.dto.MelonDTO;
import Project.melon.persistance.mongodb.impl.IMelonMapper;
import Project.melon.service.IMelonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

        LinkedList<Object> pList = new LinkedList<>();

        // 멜론 Top 100 중 50위까지 정보 가져오는 페이지
        String url = "https://www.melon.com/chart/index.htm";


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
