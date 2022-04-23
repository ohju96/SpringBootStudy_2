package mongodb.mongo.persistance.mongodb.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import mongodb.mongo.dto.MelonDTO;
import mongodb.mongo.persistance.mongodb.AbstractMongoDBComon;
import mongodb.mongo.persistance.mongodb.IMelonMapper;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component("MelonMapper")
public class MelonMapper extends AbstractMongoDBComon implements IMelonMapper {

    @Override
    public int insertSong(List<MelonDTO> pList, String cloNm) throws Exception {
        log.debug(this.getClass().getName() + ".insertSong Start");

        int res = 0;

        if (pList == null) {
            pList = new LinkedList<>();
        }

        // 부모 추상화 클래스의 인덱스 생성하여 컬렉션 생성하기
        super.createCollection(cloNm, "collectTime");

        //저장할 컬렉션 객체 생성
        MongoCollection<Document> col = mongodb.getCollection(cloNm);

        // 수집된 멜론 순위 노래 정보들 반복문을 통해 1건씩 저장하기
        for (MelonDTO melonDTO : pList) {
            if (melonDTO == null) {
                melonDTO = new MelonDTO();
            }

            // 레코드 한개씩 저장하기
            col.insertOne(new Document(new ObjectMapper().convertValue(melonDTO, Map.class)));
        }

        res = 1;

        log.debug(this.getClass().getName() + ".insertSong End!");

        return res;
    }

    @Override
    public List<MelonDTO> getSongList(String colNm) throws Exception {

        log.debug(this.getClass().getName() + ".getSongList Start");

        // 조회 결과를 전달하기 위한 객체 생성
        List<MelonDTO> rList = new LinkedList<>();

        MongoCollection<Document> col = mongodb.getCollection(colNm);

        // 조회 결과 중 출력한 컬럼들(SQL의 SELECT절과 FROM절 가운데 컬럼들과 유사하다)
        Document document = new Document();
        document.append("song", "$song");
        document.append("singer", "$singer");
        //MongoDB는 무조건 ObjectId가 자동으로 생성된다. 하지만 ObejctId는 사용하지 않을 때 조회 할 필요가 없다.
        // ObjectId를 가지고 오지 않을 때 사용한다.
        document.put("_id", 0);


        // MongoDB의 find 명령어를 통해 조회할 경우 사용한다.
        // 조회하는 데이터의 양이 적은 경우 find를 사용하고 , 데이터 양이 많으면 무조건 Aggregate를 사용한다.
        FindIterable<Document> rs = col.find(new Document()).projection(document);

        //조회 결과를 반복문을 통해 List에 담는다.
        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }

            //조회 잘되나 출력해 본다.
            String song = doc.getString("song");
            String singer = doc.getString("singer");

            log.debug("song : {}", song);
            log.debug("singer : {}", singer);

            MelonDTO melonDTO = new MelonDTO();
            melonDTO.setSong(song);
            melonDTO.setSinger(singer);

            // 레코드 결과를 List에 저장한다.
            rList.add(melonDTO);
        }
        log.debug(this.getClass().getName() + ".getSongList End");

        return rList;
    }

    @Override
    public List<MelonDTO> getSingerSongCnt(String colNm) throws Exception {

        log.debug(this.getClass().getName() + ".getSingerSongCnt Start");

        // 조회 결과를 전달하기 위해 객체 생성
        List<MelonDTO> rList = new LinkedList<>();

        /**
         * select singer, count(singer)
         * from MELON_2022.04.23
         * group by singer
         * order by count(singer)
         */
        //MongoDB 조회 쿼리, Aggregate 쿼리는 Studio 3T 자동 코드 변환기를 활용해서 변환해 주면 편하다.
        // 코드 변환 후 주의할 점으로는 Alias 명은(,)이 들어가면 DTO 변환이 불가능해서 수정해줘야 한다.
        List<? extends Bson> pipeline = Arrays.asList(
                new Document().append("$group",
                        new Document().append("_id", new Document().append("singer", "$singer")).append("COUNT(singer)",
                                new Document().append("$sum", 1))),
                new Document()
                        .append("$project",
                                new Document().append("singer", "$_id.singer").append("singerCnt", "$COUNT(singer)")
                                        .append("_id", 0)),
                new Document().append("$sort", new Document().append("singerCnt", 1)));

        MongoCollection<Document> col = mongodb.getCollection(colNm);
        AggregateIterable<Document> rs = col.aggregate(pipeline).allowDiskUse(true);

        for (Document doc : rs) {

            if (doc == null) {
                doc = new Document();
            }

            String singer = doc.getString("singer");
            int singerCnt = doc.getInteger("singerCnt", 0);

            log.debug("singer : {}", singer);
            log.debug("singerCnt : {}", singerCnt);

            MelonDTO melonDTO = new MelonDTO();
            melonDTO.setSinger(singer);
            melonDTO.setSingerCnt(singerCnt);

            rList.add(melonDTO);

            melonDTO = null;
            doc = null;
        }

        Iterator<Document> cursor = null;
        rs = null;
        col = null;
        pipeline = null;

        log.debug(this.getClass().getName() + ".getSingerSongCnt");

        return rList;
    }
}


