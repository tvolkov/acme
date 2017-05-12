package org.tvolkov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.tvolkov.model.Address;
import org.tvolkov.model.Customer;
import org.tvolkov.repository.CustomerRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ImportResource(value = "classpath:hsql_cfg.xml")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
//            Customer ivan = new Customer("ivan");
//            Set<Address> addresses1 = new HashSet<Address>(){{
//                add(new Address("Moscow, Red square", ivan));
//                add(new Address("London, Downing str", ivan));
//                add(new Address("North Pole", ivan));
//            }};
//            ivan.setAddresses(addresses1);
//
//            Customer john = new Customer("john");
//            Set<Address> addresses2 = new HashSet<Address>(){{
//               add(new Address("Los Angeles", john));
//               add(new Address("Miami", john));
//            }};
//            john.setAddresses(addresses2);
//
//            customerRepository.save(ivan);
//            customerRepository.save(john);
        };
    }
}
