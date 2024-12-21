package greatmindthinkalike.com.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class ProxyConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/login/**", "/oauth2/**")
                        .uri("http://localhost:8081"))
                .route("react", r -> r.path("/**")
                        .filters(f -> f.tokenRelay())
                        .uri("http://localhost:3000"))
                .build();
    }
}
