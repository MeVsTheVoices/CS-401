package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant, Long> {
	//these are are implemented automatically by spring, naming is important
	List<Plant> findByCommonName(String commonName);
	List<Plant> findByScientificName(String scientificName);
	List<Plant> findByTypeOfPlant(String typeOfPlant);
	List<Plant> findByMinimumGrowingZone(int minimumGrowingZone);
	List<Plant> findByMaximumGrowingZone(int maximumGrowingZone);
	//these two function are implemented by spring and are here to allow us to
	//find plants that have a maximum growing zone greater than or equal to a zone
	//and plants that have a minimum growing zone less than or equal to a zone
	//by combining the two, we can find plants that would grow in a particular zone
	List<Plant> findByMaximumGrowingZoneGreaterThanEqual(int maximumGrowingZone);
	List<Plant> findByMinimumGrowingZoneLessThanEqual(int minimumGrowingZone);
	
	//as id's are unique, we will only be returning one, not a set of plants
	Plant findById(long id);
}
