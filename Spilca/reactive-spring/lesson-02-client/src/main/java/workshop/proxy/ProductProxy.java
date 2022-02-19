package workshop.proxy;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import workshop.record.Product;

@Component
public class ProductProxy {

    private final WebClient webClient;

    public ProductProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Product> getAllProducts() {
        return webClient.get().uri("/products").exchangeToFlux(response -> response.bodyToFlux(Product.class));
    }
}
