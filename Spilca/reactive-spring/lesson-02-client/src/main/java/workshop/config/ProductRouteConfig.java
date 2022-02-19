package workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import workshop.handler.ProductHandler;

@Configuration
public class ProductRouteConfig {

    @Bean
    public RouterFunction<ServerResponse> router(ProductHandler handler) {
        return RouterFunctions.route().GET("/all-products", handler::getAllProducts).build();
    }
}
