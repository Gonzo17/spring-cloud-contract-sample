package samples.contracts.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://match-service")
@Service
public interface MatchServiceClient {

	@RequestMapping(
		method = RequestMethod.GET,
		value = "/")
	String hello();

}
