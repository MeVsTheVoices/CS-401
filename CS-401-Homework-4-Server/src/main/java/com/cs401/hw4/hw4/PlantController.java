package com.cs401.hw4.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantController {
	
	private final AtomicLong counter = new AtomicLong();
	
	static private List<Plant> plants;
	
	private final static Logger logger = Logger.getLogger(PlantController.class.getName());
	
	public PlantController() {
		//with the constructor, am adding a few simple plants to make debugging easy
		plants = new ArrayList<>();
		//stash these in a list, later versions would be better of using a database
		plants.add(new Plant("Costal redwood", "Sequoia sempervirens", "Tree", 7, 9));
		plants.add(new Plant("Salvia divinorum", "Salvia divinorum", "Perennial", 5, 9));
		plants.add(new Plant("Buddleja", "Buddleja americana", "Perennial", 5, 10));
	}
	
	@GetMapping("/plant")
	public List<Plant> plant(@RequestParam Map<String, String> params) {
		//here params will contain everything passed in the URL
		//later versions would be better allowing multiple parameters
		//to further refine our search
		
		//all pattern matching with strings is done case insensitively and is looking for
		//any plants who's field contains that string
		if (params.containsKey("common_name")) {
			//search by common name passed with the url
			logger.log(Level.INFO, "searching for a plant with common name" + params.get("common_name"));
			return plants.
					stream().
					filter(
							plant -> plant.
								getCommonName().toLowerCase().contains(params.get("common_name"))).
					collect(Collectors.toList());
		}
		else if (params.containsKey("scientific_name")) {
			logger.log(Level.INFO, "searching for a plant with scientific name" + params.get("scientific_name").toLowerCase());
			return plants.
					stream().
					filter(
							plant -> plant.
								getScientificName().toLowerCase().contains(params.get("scientific_name"))).
					collect(Collectors.toList());
		}
		else if (params.containsKey("type_of_plant")) {
			logger.log(Level.INFO, "searching for a plant with type of plant " + params.get("type_of_plant").toLowerCase());
			return plants.
					stream().
					filter(
							plant -> plant.
								getTypeOfPlant().toLowerCase().contains(params.get("type_of_plant").toLowerCase())).
					collect(Collectors.toList());
		}
		else if (params.containsKey("growing_zone")) {
			//here we're creating two lists
			//one that contains those plants with growing zones greater than
			//and one that contains those plants with growing zones less than
			logger.log(Level.INFO, "searching for a plant with growing zone " + params.get("growing_zone"));
			List<Plant> hasMaximumGrowingZoneGreaterThanEqualTo = plants.stream().filter(plant -> plant.getMaximumGrowingZone() >= Integer.valueOf(params.get("growing_zone"))).toList();
			List<Plant> hasMinimumGrowingZoneLessThanEqualTo = plants.stream().filter(plant -> plant.getMinimumGrowingZone() <= Integer.valueOf(params.get("growing_zone"))).toList();
			
			//from these lists, we then find those that are in common
			//these plants will be suitable in growing zone for the growing zone specified
			List<Plant> matchingPlants = hasMaximumGrowingZoneGreaterThanEqualTo.
				stream().filter(hasMinimumGrowingZoneLessThanEqualTo::contains).collect(Collectors.toList());
			return matchingPlants.stream().collect(Collectors.toList());
		}
		else {
			//if none of the parameters match any of the parameters defined by plant
			//then we simply throw back nothing, instead of the entire list
			logger.log(Level.WARNING, "no correct parameters supplied to search for plant" + params.toString());
			return null;
		}
	}
	
}