package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant, Long> {
	  List<Plant> findByCommonName(String CommonName);

	  Plant findById(long id);
}
