package Project.melon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * jackson 라이브러리는 int와 같은 숫자 데이터타입에
 * 저장되지 않아 발생하는 null 오류를 방지하기 위해 값이 없다면
 * 0으로 저장한다.
 * Include.NON_NULL은 NULL인 것만 방지하기 때문에 숫자 타입은 출력되는 문제가 있다.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MelonDTO {


    String collectTime; // 수집 시간
    String song; // 노래 제목
    String singer; // 가수
    int singerCnt; //차트에 등록된 가수별 노래 수


}
