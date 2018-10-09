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
        };
    }
}
