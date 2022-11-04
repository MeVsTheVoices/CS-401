package com.cs401.hw4.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantController {
	
	private final AtomicLong counter = new AtomicLong();
	
	static private List<Plant> plants;
	
	private final static Logger logger = Logger.getLogger(PlantController.class.getName());
	
	public PlantController() {
		plants = new ArrayList<>();
		plants.add(new Plant("Costal redwood", "Sequoia sempervirens", "Tree", 7, 9));
		plants.add(new Plant("Salvia divinorum", "Salvia divinorum", "Perennial", 5, 9));
		plants.add(new Plant("Buddleja", "Buddleja americana", "Perennial", 5, 10));
	}
	
	@GetMapping("/plant")
	public Plant plant(@RequestParam Map<String, String> params) {
		if (params.containsKey("common_name")) {
			logger.log(Level.INFO, "searching for a plant with common name" + params.get("common_name"));
			return plants.stream().filter(plant -> plant.getCommonName().contains(params.get("common_name"))).findFirst().orElseThrow();
		}
		else if (params.containsKey("scientific_name")) {
			logger.log(Level.INFO, "searching for a plant with scientific name" + params.get("scientific_name"));
			return plants.stream().filter(plant -> plant.getScientificName().contains(params.get("scientific_name"))).findFirst().orElseThrow();
		}
		else if (params.containsKey("type_of_plant")) {
			logger.log(Level.INFO, "searching for a plant with type of plant " + params.get("type_of_plant"));
			return plants.stream().filter(plant -> plant.getTypeOfPlant().contains(params.get("type_of_plant"))).findFirst().orElseThrow();
		}
		else if (params.containsKey("growing_zone")) {
			logger.log(Level.INFO, "searching for a plant with growing zone " + params.get("growing_zone"));
			List<Plant> hasMaximumGrowingZoneGreaterThanEqualTo = plants.stream().filter(plant -> plant.getMaximumGrowingZone() >= Integer.valueOf(params.get("growing_zone"))).toList();
			List<Plant> hasMinimumGrowingZoneLessThanEqualTo = plants.stream().filter(plant -> plant.getMinimumGrowingZone() <= Integer.valueOf(params.get("growing_zone"))).toList();
			hasMaximumGrowingZoneGreaterThanEqualTo.retainAll(hasMinimumGrowingZoneLessThanEqualTo);
			return hasMaximumGrowingZoneGreaterThanEqualTo.stream().findFirst().orElseThrow();
		}
		else {
			logger.log(Level.WARNING, "no correct parameters supplied to search for plant" + params.toString());
			return null;
		}
	}
	
}