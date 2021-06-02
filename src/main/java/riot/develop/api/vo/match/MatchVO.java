package riot.develop.api.vo.match;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchVO {

    String platformId;
    String gameId;
    String queue;
    String season;
    String timestamp;
    String role;
    String lane;

}
