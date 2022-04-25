package mongodb.mongo.persistance.mongodb;

import com.mongodb.client.model.Indexes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
public abstract class AbstractMongoDBComon {

    //MongoDB 객체를 상속받는 Java class도 사용하기 위해 접근 제어자를 protected로 설정한다.
    @Autowired
    protected MongoTemplate mongodb;

    /**
     * 컬렉션 생성
     * @param colNm 생성할 컬렉션명
     * @return 생성결과
     */
    protected boolean createCollection(String colNm) {

        boolean res;

        if (mongodb.collectionExists(colNm)) {
            res = false;
        } else {
            mongodb.collectionExists(colNm);
            res = true;
        }
        return res;
    }

    /**
     * 인덱스 컬럼이 한 개일때 컬렉션 생성
     * @param colNm 생성할 컬렉션명
     * @param index 생성할 인덱스
     * @return 생성 결과
     */
    protected boolean createCollection(String colNm, String index) {

        String[] indexArr = {index};

        return createCollection(colNm, indexArr);
    }

    /**
     * 인덱스 컬럼이 여러 개일 때 컬렉션 생성
     * @param colNm
     * @param index
     * @return
     */
    protected boolean createCollection(String colNm, String[] index) {

        boolean res = false;

        // 기존에 등록된 컬렉션 이름이 존재하는지 체크, 없으면 생성
        if (!mongodb.collectionExists(colNm)) {

            // 인덱스 값이 존재한다면..
            if (index.length > 0) {

                // 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성
                // 데이터 양이 많지 않으면 상관이 없는데 10만건 이상 데이터 저장 시 속도가 10배 이상 차이남
                mongodb.createCollection(colNm).createIndex(Indexes.ascending(index));

            } else {

                // 인덱스가 없으면 인덱스 없이 컬렉션 생성
                mongodb.createCollection(colNm);

            }

            res = true;
        }

        return res;
    }

    /**
     * 컬렉션 삭제
     * @param colNm 생성할 컬렉션명
     * @return 삭제 결과
     */
    protected boolean dropCollection(String colNm) {

        boolean res = false;

        // 컬렉션이 존재할 때만 삭제한다.
        if (mongodb.collectionExists(colNm)) {
            mongodb.dropCollection(colNm);
            res = true;
        }

        return res;
    }

}
