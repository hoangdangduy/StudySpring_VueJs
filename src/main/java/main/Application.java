package main;

import com.dom.Product;
import com.dom.User;
import com.repo.ProductRepository;
import com.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.api","com.web","com.dom","com.repo"} )
@EnableJpaRepositories(basePackages = {"com.dom", "com.repo"})
@EntityScan(basePackages = { "com.dom","com.repo"} )
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository, ProductRepository productRepository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new User("admin", "admin@admin.com", "admin"));


            // fetch all user
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(user -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(user.toString());
                        log.info("");
                    });

            productRepository.save(new Product("SAN PHAM 1", 1, "san pham cung tot day"));
            productRepository.save(new Product("SAN PHAM 2", 2, "san pham thuoc trung binh"));
            productRepository.save(new Product("SAN PHAM 3", 3, "san pham kha"));
            productRepository.save(new Product("SAN PHAM 4", 4, "san pham tot"));
        };
    }
}
