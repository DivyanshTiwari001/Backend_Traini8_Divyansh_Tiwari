package com.github.divyanshtiwari001.traini8.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.divyanshtiwari001.traini8.model.Center;
import com.github.divyanshtiwari001.traini8.model.Address;
import com.github.divyanshtiwari001.traini8.repository.CenterRepository;


@Configuration
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CenterRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Center("Tech Learning Hub","123456754565",new Address("123 Main Street", "New Delhi", "Delhi", "110001"),100L,null,null,"1234567890")));
            log.info("Preloading " + repository.save(new Center("Techo Point","123456789875",new Address("124 Main Street", "Mumbai", "Maharastra", "272002"),100L,null,null,"1234567890")));
        };
    }
}
