package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

  private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class);
  }

  @Bean
  public CommandLineRunner demo(PlantRepository repository) {
    return (args) -> {
      // save a few plants
      repository.save(new Plant("Coastal redwood", "Sequoia sempervirens", "Tree"));
      repository.save(new Plant("Salvia divinorum", "Salvia divinorum", "Perennial"));
      repository.save(new Plant("Buddleja", "Buddleja americana", "Perennial"));

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (Plant customer : repository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual plant by ID
      Plant customer = repository.findById(1L);
      log.info("Plant found with findById(1L):");
      log.info("--------------------------------");
      log.info(customer.toString());
      log.info("");

      // fetch plants by common name
      log.info("Plant found with findByCommonName('Coastal redwood'):");
      log.info("--------------------------------------------");
      repository.findByCommonName("Coastal redwood").forEach(plant -> {
        log.info(plant.toString());
      });

      log.info("");
    };
  }

}