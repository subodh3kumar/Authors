package workshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import workshop.entity.Customer;
import workshop.mapper.CustomerMapper;

@Slf4j
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(CustomerMapper mapper) {
        return args -> {
            var customer = new Customer(1, "Subodh", "subodh@java.com");
            mapper.insert(customer);

            var result = mapper.findById(customer.getId());

            log.info("inserted: {}", customer);
            log.info("find: {}", result);
        };
    }
}
