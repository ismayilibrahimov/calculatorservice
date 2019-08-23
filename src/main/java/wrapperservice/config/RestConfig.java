package wrapperservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = builder.build();
        // RestRequestInterceptor does not work,
        // so I've hardcoded rest request logging in the controller
        restTemplate.setInterceptors(Collections.singletonList(new RestRequestInterceptor()));
        return restTemplate;
    }


}
