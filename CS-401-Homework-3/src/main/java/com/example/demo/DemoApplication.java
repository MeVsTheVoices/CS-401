package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

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
	  
	  //a few basic tests to ensure that I haven't broken anything
	  
    return (args) -> {
      // save a few plants, id's are generated automatically
      repository.save(new Plant("Coastal redwood", "Sequoia sempervirens", "Tree", 7, 9));
      repository.save(new Plant("Salvia divinorum", "Salvia divinorum", "Perennial", 5, 9));
      repository.save(new Plant("Buddleja", "Buddleja americana", "Perennial", 5, 10));

      // fetch all plants
      log.info("Plants found with findAll():");
      log.info("-------------------------------");
      for (Plant customer : repository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual plant by ID
      Plant customer = repository.findById(1L);
      log.info("Plants found with findById(1L):");
      log.info("--------------------------------");
      log.info(customer.toString());
      log.info("");
      
      //now to test that we can still provide the functionality from hw1

      // fetch plants by common name
      log.info("Plants found with findByCommonName('Coastal redwood'):");
      log.info("--------------------------------------------");
      repository.findByCommonName("Coastal redwood").forEach(plant -> {
        log.info(plant.toString());
      });
      log.info("");
      
      // fetch plants by scientific name
      log.info("Plants found with findByScientificName('Salvia divinorum'):");
      log.info("--------------------------------------------");
      repository.findByScientificName("Salvia divinorum").forEach(plant -> {
        log.info(plant.toString());
      });
      log.info("");
      
      // fetch plants by type of plant
      log.info("Plants found with findByTypeOfPlant('Perennial'):");
      log.info("--------------------------------------------");
      repository.findByTypeOfPlant("Perennial").forEach(plant -> {
        log.info(plant.toString());
      });
      log.info("");
      
      // fetch plants by min growing zone
      log.info("Plants found with findByMinimumGrowingZone(5):");
      log.info("--------------------------------------------");
      repository.findByMinimumGrowingZone(5).forEach(plant -> {
        log.info(plant.toString());
      });
      log.info("");
      
      // fetch plants by max growing zone
      log.info("Plants found with findByMaximumGrowingZone(9):");
      log.info("--------------------------------------------");
      repository.findByMaximumGrowingZone(9).forEach(plant -> {
        log.info(plant.toString());
      });
      log.info("");

      
      //this is going to be hacky, but, without a better understanding of spring, this will do for now
      //grab any plants that have a maximum zone greater than
      List<Plant> haveGreaterThanEqualMaxZone = repository.findByMaximumGrowingZoneGreaterThanEqual(6);
      //grab any plants that have a minimum zone less than
      List<Plant> haveLessThanEqualMinZone = repository.findByMinimumGrowingZoneLessThanEqual(6);
      //take what's common, that is where minZone <= value <= maxZone
      haveGreaterThanEqualMaxZone.retainAll(haveLessThanEqualMinZone);
      List<Plant> plantsInZone = haveGreaterThanEqualMaxZone;
      
      //display those plants that we found that have a growing zone between the max and min
      log.info("Plants found with a growing zone of 6:");
      log.info("--------------------------------------------");
      haveLessThanEqualMinZone.forEach(plant -> {
        log.info(plant.toString());
      });
      log.info("");
    };
  }

}