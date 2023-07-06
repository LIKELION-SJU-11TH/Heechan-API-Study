package com.example.demo.Controller;

import com.example.demo.dto.BikeInfo;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BikeController {
    @PostMapping("/api/{START_INDEX}/{END_INDEX}")
    public List<BikeInfo> load_save(@PathVariable("START_INDEX") String start,
                                    @PathVariable("END_INDEX") String end
    ){
        String result = "";
        List<BikeInfo> bikeInfoList = new ArrayList<>();
        try {

            /** API를 받아올 주소 만들기 */

            URL url = new URL("http://openapi.seoul.go.kr:8088/" + "API 인증키" +
                    "/json/bikeList/"+start+"/"+end);

            /** 크롬에서  http://openapi.seoul.go.kr:8088/(인증키)/json/bikeList/1/5/ 를 쳤을 때 나오는 JSON구조를 문자열 result에 저장 */
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();
            log.info("result:{}",result);


            /** result는 String이지만 Json구조이기 때문에 그 구조에서 필요한 부분만 뺴오기위해 JSONParser를 이용해야한다
             * 주의해야할점은
             * implementation group: 'com.googlecode.json-simple', name: 'json-simple', version: '1.1'
             * 위의 dependencies를 입력해주고 JSONParser를 improt하는 과정에서 의존성을 추가해준 라이브러리를 호출받아야한다.
             * */
            JSONParser jsonParser = new JSONParser();

            /**  로그 기록을 따라 가면서 어떤 과정을 통해 parsing 하는지 살펴보기 */
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            log.info("result:{}",jsonObject);


            JSONObject rentBikeStatus = (JSONObject)jsonObject.get("rentBikeStatus");
            log.info("result:{}",rentBikeStatus);


            JSONArray infoArr = (JSONArray) rentBikeStatus.get("row");
            log.info("result:{}",infoArr);


            /** Parsing 된 infoArr을 list에 담아서 해당 내용을 출력하는 것이다. */
            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                BikeInfo infoObj=new BikeInfo(i+(long)1,
                        Integer.parseInt((String)tmp.get("shared")),
                        Integer.parseInt((String)tmp.get("parkingBikeTotCnt")),
                        (String)tmp.get("stationName"),
                        Double.parseDouble((String)tmp.get("stationLatitude")),
                        Double.parseDouble((String)tmp.get("stationLongitude")),
                        Integer.parseInt((String)tmp.get("rackTotCnt")),
                        (String)tmp.get("stationId"));

                bikeInfoList.add(infoObj);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return bikeInfoList;
    }
}
