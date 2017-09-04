package samples.contracts.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.asList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExternalApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles(profiles = "test")
public class ExternalApiControllerTest {

	public static final String PACKAGE = "com.github.gonzo17.samples";
	public static final String PLAYER_SERVICE = "player-service";

	@Rule
	public StubRunnerRule rule = new StubRunnerRule()
		.downloadStub(PACKAGE, PLAYER_SERVICE)
		.workOffline(false);

	private MockMvc mockMvc;

	@Before
	public void setup() {
		final RestTemplate restTemplate = new RestTemplate();

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(asList(APPLICATION_JSON, APPLICATION_OCTET_STREAM));

		restTemplate.setMessageConverters(asList(converter, new FormHttpMessageConverter()));

		int port = rule.findStubUrl(PLAYER_SERVICE).getPort();

		ExternalApiController externalApiController = new ExternalApiController(new FakePlayerServiceClient(restTemplate, port));

		this.mockMvc = MockMvcBuilders.standaloneSetup(externalApiController).build();
	}

	@Test
	public void testExternalApi() throws Exception {
		this.mockMvc.perform(get("/api/player/gonzo"))
			.andExpect(jsonPath("$.id").value(1L))
			.andExpect(jsonPath("$.name").value("gonzo"))
			.andExpect(jsonPath("$.mail").value("gonzo@mail.com"));
	}
}
