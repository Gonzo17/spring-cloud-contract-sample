package samples.contracts.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import samples.contracts.common.domain.PlayerInfoDto;

@FeignClient("http://player-service")
@Service
public interface PlayerServiceClient {

	@RequestMapping(method = RequestMethod.GET,
		value = "/api/player/{playerName}")
	PlayerInfoDto getPlayerInfo(@PathVariable(value = "playerName") String playerName);
}
