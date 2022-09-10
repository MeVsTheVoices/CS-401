package plants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//basic tests for plantInfo functionality
class PlantInfoTest {
	
	Plant[] plants = new Plant[3];

	PlantInfo plantInfo;
	
	//create some basic plants with which to populate our plantInfo class
	@BeforeEach
	void setUp() throws Exception {
		plantInfo = new PlantInfo();
		
		plants[0] = new Plant();
		plants[0].setCommonName("Coastal redwood");
		plants[0].setScientificName("Sequoia sempervirens");
		plants[0].setTypeOfPlant("Tree");
		plants[0].setMinimumGrowingZone(7);
		plants[0].setMaximumGrowingZone(9);
		
		plants[1] = new Plant();
		plants[1].setCommonName("Salvia divinorum");
		plants[1].setScientificName("Salvia divinorum");
		plants[1].setTypeOfPlant("Perennial");
		plants[1].setMinimumGrowingZone(5);
		plants[1].setMaximumGrowingZone(9);
		
		plants[2] = new Plant();
		plants[2].setCommonName("Buddleja");
		plants[2].setScientificName("Buddleja americana");
		plants[2].setTypeOfPlant("Perennial");
		plants[2].setMinimumGrowingZone(5);
		plants[2].setMaximumGrowingZone(10);
	}

	//check that plantInfo is behaving as it should be
	@Test
	void test() {
		testAddingPlants();
		testGettingPlants();
		testGrowingZoneGetBy();
		testScientificGetBy();
	}
	
	void testAddingPlants() {
		//check that we can't add duplicates by common name
		plantInfo.addPlant(plants[0]);
		plantInfo.addPlant(plants[1]);
		plantInfo.addPlant(plants[2]);
	}
	
	void testGettingPlants() {
		//basic retrieval, fail here first, makes finding the problem easier
		assert(!plantInfo.getPlantsByCommonName("redwood").isEmpty());
		assert(!plantInfo.getPlantsByScientificName("Seq sem").isEmpty());
		assert(!plantInfo.getPlantsByTypeOfPlant("Tree").isEmpty());
	}
	
	void testGrowingZoneGetBy() {
		//check functionality to ensure that growing zone is treated as inclusive bounds
		assert(!plantInfo.getPlantsByGrowingZone(8).isEmpty());
		assert(!plantInfo.getPlantsByGrowingZone(9).isEmpty());
		assert(!plantInfo.getPlantsByGrowingZone(7).isEmpty());
		assert(!plantInfo.getPlantsByGrowingZone(6).isEmpty());
		assert(!plantInfo.getPlantsByGrowingZone(10).isEmpty());
		assert(plantInfo.getPlantsByGrowingZone(2).isEmpty());
		assert(plantInfo.getPlantsByGrowingZone(11).isEmpty());	}
	
	void testScientificGetBy() {
		//these are added since the previous submission to deal with the new scientific name search
		assert(plantInfo.getPlantsByScientificName("B b").isEmpty());
		assert(!plantInfo.getPlantsByScientificName("Seq sem").isEmpty());
		assert(!plantInfo.getPlantsByScientificName("Sa di").isEmpty());
		assert(!plantInfo.getPlantsByScientificName("Bu am").isEmpty());
	}

}
