package samples.contracts.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import samples.contracts.api.client.PlayerServiceClient;
import samples.contracts.common.domain.PlayerInfoDto;

@Controller
@RequestMapping("/api")
public class ExternalApiController {

	private final PlayerServiceClient playerServiceClient;

	@Autowired
	public ExternalApiController(PlayerServiceClient playerServiceClient) {
		this.playerServiceClient = playerServiceClient;
	}

	@RequestMapping("/player/{playerName}")
	@ResponseBody
	public PlayerInfoDto playerInfo(@PathVariable String playerName) {
		return playerServiceClient.getPlayerInfo(playerName);
	}
}
