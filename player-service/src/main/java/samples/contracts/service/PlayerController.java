package samples.contracts.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import samples.contracts.common.domain.PlayerInfoDto;

@Controller
@RequestMapping("/api")
public class PlayerController {

	@RequestMapping("/player/{playerName}")
	@ResponseBody
	public PlayerInfoDto getPlayerInfo(@PathVariable String playerName) {
		if (playerName.equalsIgnoreCase("gonzo")) {
			return PlayerInfoDto.builder().id(1L).name("gonzo").mail("gonzo@mail.com").build();
		} else {
			throw new PlayerNotFoundException();
		}
	}
}
