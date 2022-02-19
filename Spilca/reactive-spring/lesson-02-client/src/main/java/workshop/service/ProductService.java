package workshop.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import workshop.proxy.ProductProxy;
import workshop.record.Product;

@Service
public class ProductService {

    private final ProductProxy productProxy;

    public ProductService(ProductProxy productProxy) {
        this.productProxy = productProxy;
    }

    public Flux<Product> getAllProducts() {
        return productProxy.getAllProducts();
    }
}
