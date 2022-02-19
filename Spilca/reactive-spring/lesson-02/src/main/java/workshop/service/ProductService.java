package workshop.service;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import workshop.record.Product;

@Slf4j
@Service
public class ProductService {

	public Flux<Product> getAllProducts() {
		log.info("getAllProducts() method start");

		Product beer = new Product("Beer");
		Product book = new Product("Book");
		Product sweet = new Product("Sweet");
		Product chocolate = new Product("Chocolate");

		List<Product> products = List.of(beer, book, sweet, chocolate);

		log.info("products size: {}", products.size());

		return Flux.fromStream(products.stream()).delayElements(Duration.ofSeconds(2));

	}

}
