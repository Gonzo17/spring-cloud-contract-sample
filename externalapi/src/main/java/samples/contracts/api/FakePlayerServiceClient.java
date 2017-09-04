package samples.contracts.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import samples.contracts.api.client.PlayerServiceClient;
import samples.contracts.common.domain.PlayerInfoDto;

import java.net.URI;

@RequiredArgsConstructor
public class FakePlayerServiceClient implements PlayerServiceClient {

	private final RestTemplate restTemplate;
	private final int port;

	@Override
	public PlayerInfoDto getPlayerInfo(final String playerName) {
		ResponseEntity<PlayerInfoDto> response = this.restTemplate.exchange(
			RequestEntity
				.get(URI.create("http://localhost:" + port + "/api/player/" + playerName))
				.build(),
			PlayerInfoDto.class);
		return response.getBody();
	}
}
