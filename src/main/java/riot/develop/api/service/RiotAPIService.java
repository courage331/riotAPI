package riot.develop.api.service;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import riot.develop.api.vo.ResponseVO;
import riot.develop.api.vo.SummonerVO;

import java.util.HashMap;
import java.util.Map;

@Service
public class RiotAPIService {

    @Autowired
    Gson gson;

    RestTemplate restTemplate = new RestTemplate();

    public ResponseVO findSummoner(String summonerName) {
        ResponseVO responseVO = new ResponseVO();
        String AccessKey = "RGAPI-c3d02e5d-f506-4f63-85cb-a86337fd1cce";
        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name";
        Map<String,String> param = new HashMap<>();
        param.put("summonerName",summonerName);
        try {

            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
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
}
