package fr.campus.spring.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

    @Bean
    @Order(-1) // Ensures this filter runs first
    public GlobalFilter logRequestFilter() {
        return (exchange, chain) -> {
            ServerWebExchange requestExchange = exchange;
            logger.info("Incoming request - Method: {}, URI: {}",
                        requestExchange.getRequest().getMethod(),
                        requestExchange.getRequest().getURI());

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Response Status Code: {}", exchange.getResponse().getStatusCode());
            }));
        };
    }
	
}
