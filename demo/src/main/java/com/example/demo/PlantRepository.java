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
	List<Plant> findByMaximumGrowingZoneGreaterThanEqual(int maximumGrowingZone);
	List<Plant> findByMinimumGrowingZoneLessThanEqual(int minimumGrowingZone);
	
	//as id's are unique, we will only be returning one, not a set of plants
	Plant findById(long id);
}
