package samples.contracts.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
public class MatchServiceApplication extends WebMvcConfigurerAdapter {

	public static void main(final String[] args) {
		SpringApplication.run(MatchServiceApplication.class, args);
	}

}
