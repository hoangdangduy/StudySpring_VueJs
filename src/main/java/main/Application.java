package main;

import com.dom.Comment;
import com.dom.Product;
import com.dom.User;
import com.repo.CommentRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public CommandLineRunner demo(UserRepository userRepository, ProductRepository productRepository, CommentRepository commentRepository) {
        return (args) -> {

            List<String> username = new ArrayList<>();

            // save a couple of customers
            userRepository.save(new User("admin", "admin@admin.com", "admin"));
            userRepository.save(new User("hoangdd", "hoangdd@hoangdd.com", "hoangdd"));

            userRepository.findAll().forEach(domUser -> {
                username.add(domUser.getUsername());
            });

            productRepository.save(new Product("SAN PHAM 1", 0, Collections.emptyList()));
            productRepository.save(new Product("SAN PHAM 2", 0, Collections.emptyList()));
            productRepository.save(new Product("SAN PHAM 3", 0, Collections.emptyList()));
            productRepository.save(new Product("SAN PHAM 4", 0, Collections.emptyList()));
            productRepository.save(new Product("SAN PHAM 5", 0, Collections.emptyList()));
            productRepository.save(new Product("SAN PHAM 6", 0, Collections.emptyList()));
            productRepository.save(new Product("SAN PHAM 7", 0, Collections.emptyList()));

            // Get all product
            List<Product> lstProduct= StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());

            commentRepository.save(new Comment(username.get(0), "san pham tot 1", 1, lstProduct.get(0)));
            commentRepository.save(new Comment(username.get(1), "san pham tot 2", 2, lstProduct.get(0)));

            // Calculate avarage rank
            Product product = productRepository.findById(lstProduct.get(0).getId()).get();
            Double averageRank = product.getLstComment().stream().mapToDouble(Comment::getRank).average().getAsDouble();
            product.setRank(averageRank.floatValue());
            productRepository.save(product);

            // fetch all user
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            userRepository.findById(1L)
                    .ifPresent(user -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(user.toString());
                        log.info("");
                    });



        };
    }
}
