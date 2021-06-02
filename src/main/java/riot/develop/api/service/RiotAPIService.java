package riot.develop.api.service;


import com.google.gson.Gson;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import riot.develop.api.vo.ResponseVO;
import riot.develop.api.vo.match.MatchList;
import riot.develop.api.vo.summoner.SummonerVO;

import java.util.HashMap;
import java.util.Map;

@Service
public class RiotAPIService {

    @Autowired
    Gson gson;

    RestTemplate restTemplate = new RestTemplate();

    String AccessKey = "RGAPI-aba3f295-e742-4198-b991-f2c1c03dde8f";

    @Value("${riot.endpoint.summoner}")
    String summonerurl;

    @Value("${riot.endpoint.matches}")
    String matchurl;

    public ResponseVO findSummoner(String summonerName) {
        ResponseVO responseVO = new ResponseVO();

        Map<String,String> param = new HashMap<>();
        param.put("summonerName",summonerName);
        try {

            UriComponents builder = UriComponentsBuilder.fromHttpUrl(summonerurl)
                    .path("/{summonerName}")
                    .queryParam("api_key",AccessKey)
                    .encode() //UTF-8 encoding해줌 toUri()로 URI타입을 넘기는 경우 사용
                    .buildAndExpand(param);

            SummonerVO summonerVO = restTemplate.getForObject(builder.toUri(),SummonerVO.class);


            responseVO.setData(summonerVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        responseVO.setResponseCode(200);
        responseVO.setResponseMsg("Success");


        return responseVO;
    }

    public ResponseVO showRecentMatches(String summonerName){
        ResponseVO responseVO = new ResponseVO();
        String accountId = String.valueOf(findSummoner(summonerName).getData()).split(",|=")[3];

        Map<String,String> param = new HashMap<>();
        param.put("accountId",accountId);
        MatchList mathchList = new MatchList();
        try {

            UriComponents builder = UriComponentsBuilder.fromHttpUrl(matchurl)
                    .path("/{accountId}")
                    .queryParam("api_key",AccessKey)
                    .encode() //UTF-8 encoding해줌 toUri()로 URI타입을 넘기는 경우 사용
                    .buildAndExpand(param);

            System.out.println(builder.toUri());
            mathchList = restTemplate.getForObject(builder.toUri(), MatchList.class);
            responseVO.setData(mathchList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        responseVO.setResponseCode(200);
        responseVO.setResponseMsg("Success");

        return responseVO;
    }
}
