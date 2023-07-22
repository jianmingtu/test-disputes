package ca.bc.gov.iamp.justindisputesapi.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    private final TimeoutProperties timeoutProperties;

    public RestTemplateConfiguration(TimeoutProperties timeoutProperties) {
        this.timeoutProperties = timeoutProperties;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(timeoutProperties.getConnect());
        factory.setReadTimeout(timeoutProperties.getRead());

        return new RestTemplate(factory);
    }
}
