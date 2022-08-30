package plants;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

//aggregate and help search through plants in a collection
public class PlantInfo {
	private TreeSet<Plant> mPlants;
	
	//keep plants sorted by common name in memory
	public PlantInfo() {
		mPlants = new TreeSet<Plant>(
				(Plant plantLHS, Plant plantRHS)->
					plantLHS.getCommonName().compareTo(plantRHS.getCommonName()));
	}
	
	//allow plants to be added, a false result means that it wasn't added,
	//as it would be a duplicate by common name
	public boolean addPlant(Plant aPlant) {
		return mPlants.add(aPlant);
	}
	
	//utility functions to return matches for various attributes
	//filter by common name
	public List<Plant> getPlantsByCommonName(String commonName) {
		return mPlants.stream().filter(
				plantLHS->
					plantLHS.getCommonName().contains(commonName)).collect(Collectors.toList());
	}

	//filter by scientific name
	public List<Plant> getPlantsByScientificName(String scientificName) {
		return mPlants.stream().filter(
				plantLHS->
					plantLHS.getScientificName().contains(scientificName)).collect(Collectors.toList());
	}

	//filter by type of plant
	public List<Plant> getPlantsByTypeOfPlant(String typeOfPlant) {
		return mPlants.stream().filter(
				plantLHS->
					plantLHS.getTypeOfPlant().contains(typeOfPlant)).collect(Collectors.toList());
	}
	
	//filter by growing zone
	public List<Plant> getPlantsByGrowingZone(int growingZone) {
		return mPlants.stream().filter(
				plantLHS->
				   plantLHS.getMaximumGrowingZone() >= growingZone
				&& plantLHS.getMinimumGrowingZone() <= growingZone)
					.collect(Collectors.toList());
	}
}
