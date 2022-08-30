package plants;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;


//this intends to drive the PlantInfo and Plant classes to create
//behavoire like that shown in demo
public class PlantDriver {

	//keep asking the user what they want to do
	public static void main(String[] args) {
		System.out.println("Welcome");
		String menuPrompt = 
				"0 to Quit\n" +
				"1 to Add\n"  +
				"2 to Find by type\n" +
				"3 to Find by zone\n" +
				"4 to Find by name: ";
		
		Scanner aScanner = new Scanner(System.in);
		
		boolean keepAsking = true;
		
		PlantInfo aPlantInfo = new PlantInfo();
		
		while (keepAsking) {
			
			//reject invalid input
			int menuValue = -1;
			while (rejectInput(menuValue)) {
				System.out.print(menuPrompt);
				menuValue = aScanner.nextInt();
			}
			
			//deal with the users choice
			switch (menuValue) {
			case 0:
				keepAsking = false;
				break;
			case 1:
				aPlantInfo.addPlant(getPlantFromUser(aScanner));
				break;
			case 2:
				System.out.println("Enter a type: ");
				aScanner.nextLine();
				String type = aScanner.nextLine();
				listPlants(findPlants(aPlantInfo, type, aPlantInfo::getPlantsByTypeOfPlant));
				break;
			case 3:
				System.out.println("Enter a zone: ");
				aScanner.nextLine();
				int zone = aScanner.nextInt();
				listPlants(findPlants(aPlantInfo, zone, aPlantInfo::getPlantsByGrowingZone));
				break;
			case 4:
				System.out.println("Enter a name: ");
				aScanner.nextLine();
				String name = aScanner.nextLine();
				listPlants(findPlants(aPlantInfo, name, aPlantInfo::getPlantsByCommonName));
				break;
			default:
				break;
			}
			
			
		}
	}
	
	//utility method, uses PlantInfo to get a list of plants that contain those values
	protected static <T extends Comparable<T>> List<Plant> findPlants(
			PlantInfo plantInfo, T value, Function<T, List<Plant>> memberFunction) {
		return memberFunction.apply(value);
	}
	
	//build a string to display a list of plants, then print it to console
	protected static void listPlants(List<Plant> plants) {
		
		//we're creating a formatted string from the plants in a list
		String out = plants.stream().map((plant) -> {
			StringBuilder str = new StringBuilder();
			
			str.append("Common name: ");
				str.append(plant.getCommonName());
				str.append('\n');
			str.append("Scientific name: ");
				str.append(plant.getScientificName());
				str.append('\n');
			str.append("Type of plant: ");
				str.append(plant.getTypeOfPlant());
				str.append('\n');
			str.append("Minimum hardiness zone: ");
				str.append(plant.getMinimumGrowingZone());
				str.append('\n');
			str.append("Maximum hardiness zone: ");
				str.append(plant.getMaximumGrowingZone());
				str.append('\n');
				
			return str.toString();
		}).collect(Collectors.joining("\n"));
		System.out.println(out);
	}
	
	//return false for any user input outside of bounds
	private static boolean rejectInput(int input) {
		if (input <= 4 && input >= 0)
			return false;
		else
			return true;
	}
	
	//ask the user the details for a new plant they'd like to add
	private static Plant getPlantFromUser(Scanner aScanner) {
		Plant aPlant = new Plant();
		aScanner.nextLine();
		System.out.print("Enter the common name of the plant: ");
		aPlant.setCommonName(aScanner.nextLine());
		System.out.print("Enter the scientific name of the plant: ");
		aPlant.setScientificName(aScanner.nextLine());
		System.out.print("Enter the minimum planting zone of the plant: ");
		aPlant.setMinimumGrowingZone(aScanner.nextInt());
		System.out.print("Enter the maximum planting zone of the plant: ");
		aPlant.setMaximumGrowingZone(aScanner.nextInt());
		System.out.print("Enter the type of the plant -- 0 for tree, 1 for perennial: ");
		int type = aScanner.nextInt();
		aPlant.setTypeOfPlant(type == 0 ? "Tree" : "Perennial");
		
		return aPlant;
	}

}
