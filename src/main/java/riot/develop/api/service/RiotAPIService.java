package riot.develop.api.service;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import riot.develop.api.vo.ResponseVO;
import riot.develop.api.vo.SummonerVO;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Service
public class RiotAPIService {

    @Autowired
    Gson gson;

    RestTemplate restTemplate = new RestTemplate();

    public ResponseVO findSummoner(String summonerName) {
        ResponseVO responseVO = new ResponseVO();
        String AccessKey = "";
        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name";
        Map<String,String> param = new HashMap<>();
        param.put("summonerName",summonerName);
        try {
//            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//            System.out.println(url);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/json;charset=utf-8");

            UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                    .path("/{summonerName}")
                    .queryParam("api_key",AccessKey)
                    .encode() //UTF-8 encoding해줌 toUri()로 URI타입을 넘기는 경우 사용
                    .buildAndExpand(param);

//            final HttpEntity<String> entity = new HttpEntity(headers);
            System.out.println(builder.toUriString());
            url += "?api_key=" + AccessKey;
//            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            SummonerVO summonerVO = restTemplate.getForObject(builder.toUri(),SummonerVO.class);

//            SummonerVO summonerVO = gson.fromJson(response.getBody(), SummonerVO.class);

            responseVO.setData(summonerVO);

        } catch (Exception e) {
            e.printStackTrace();
        }
        responseVO.setResponseCode(200);
        responseVO.setResponseMsg("Success");
        return responseVO;
    }
}
