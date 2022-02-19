package workshop.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import workshop.record.Product;
import workshop.service.ProductService;

@Component
public class ProductHandler {

    private final ProductService service;

    public ProductHandler(ProductService service) {
        this.service = service;
    }

    public Mono<ServerResponse> getAllProducts(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(service.getAllProducts(), Product.class);
    }
}
