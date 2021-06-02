package riot.develop.api.vo.summoner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummonerVO {

    String id;
    String accountId;
    String puuid;
    String name;
    String profileIconId;
    String revisionDate;
    String summonerLevel;
}
