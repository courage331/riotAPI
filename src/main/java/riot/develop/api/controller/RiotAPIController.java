package riot.develop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import riot.develop.api.service.RiotAPIService;
import riot.develop.api.vo.ResponseVO;

@RestController
@RequestMapping("/riot")
public class RiotAPIController {

    @Autowired
    RiotAPIService riotAPIService;

    @RequestMapping(method = RequestMethod.GET, value="/test")
    public ResponseVO TestController(@RequestParam(value="summonerName") String summonerName){

        ResponseVO responseVO = riotAPIService.findSummoner(summonerName);
        return responseVO;
    }
}
