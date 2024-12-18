package org.sid.hh;

import lombok.Builder;
import org.sid.hh.entities.Product;
import org.sid.hh.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class HhApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhApplication.class, args);
    }
    @Bean
    CommandLineRunner start (ProductRepository productRepository
            , RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(List.of(
                            Product.builder().name("hassan").quantity(12).price(5).build(),
                            Product.builder().name("r").quantity(2).price(2).build(),
                            Product.builder().name("h").quantity(1).price(5).build()
                    )
            );
            productRepository.findAll().forEach(c->{
                System.out.println(c);
            });
        };
    }

}
