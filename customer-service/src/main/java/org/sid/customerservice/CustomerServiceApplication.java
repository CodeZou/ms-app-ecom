package org.sid.customerservice;

import org.sid.customerservice.config.CustomerConfigParams;
import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository
    , RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(List.of(
                    Customer.builder().name("hassan").email("hasan@gmail.com").build(),
                    Customer.builder().name("pipox").email("pioox@gmail.com").build(),
                    Customer.builder().name("omar").email("omar@gmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(c->{
                System.out.println(c);
            });
        };
    }
}
