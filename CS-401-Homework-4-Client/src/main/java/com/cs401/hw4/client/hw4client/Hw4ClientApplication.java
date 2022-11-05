package com.cs401.hw4.client.hw4client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.cs401.hw4.hw4.Plant;

import java.util.function.BiFunction;
import java.util.function.Function;

@SpringBootApplication
public class Hw4ClientApplication {
	
	private final static Logger logger = Logger.getLogger(Hw4ClientApplication.class.getName());
	
	private Map<String, BiFunction<String, RestTemplate, List<Plant>>> searchFunctions;
	
	public Hw4ClientApplication() {
		searchFunctions = new HashMap<>();
		searchFunctions.put("common name", (value, template) -> {
			return searchFor("common_name", template, value);
		});
		searchFunctions.put("scientific name", (value, template) -> {
			return searchFor("scientific_name", template, value);
		});
		searchFunctions.put("type of plant", (value, template) -> {
			return searchFor("type_of_plant", template, value);
		});
		searchFunctions.put("growing zone", (value, template) -> {
			return searchFor("growing_zone", template, value);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(Hw4ClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			System.out.println("enter exit to escape");
			while (true) {
				//get ready for some console input
				Scanner scanner = new Scanner(System.in);
				//go through the keys in searching functions and display those to the user
				System.out.print(searchFunctions.keySet().stream().collect(Collectors.joining(", ", "", ": ")));
				//get the users choice
				String choice = scanner.nextLine();
				if (choice.toLowerCase().contains("exit"))
					break;
				//case insensitive, find any of the choices that contain the user input
				String key = searchFunctions.keySet().stream().map(s -> s.toLowerCase()).filter(s -> s.contains(choice)).findFirst().orElse("");
				if (key == "")
					System.out.println("invalid choice");
				else {
					System.out.print("enter " + key + " to search for: ");
					String value = scanner.nextLine();
					List<Plant> returnedPlants = searchFunctions.get(key).apply(value, restTemplate);
					System.out.println(returnedPlants);
				}
			}
			
			Plant[] plants = restTemplate.getForObject(
					"http://localhost:8080/plant?growing_zone=7", Plant[].class);
			logger.info(Arrays.asList(plants).toString());
		};
	}
	
	private List<Plant> searchFor(String type, RestTemplate template, String value) {
		final String url = "http://localhost:8080/plant?" + type+ "=" + value;
		Plant[] plants = template.getForObject(
				"http://localhost:8080/plant?" + type + "=" + value, Plant[].class);
		List<Plant> plantList = Arrays.asList(plants);
		logger.info("got\n" + plantList + "\n from " + url);
		return plantList;
	}

}
