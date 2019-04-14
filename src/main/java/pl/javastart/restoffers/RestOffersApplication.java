package pl.javastart.restoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.javastart.restoffers")
public class RestOffersApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestOffersApplication.class, args);
    }
}
