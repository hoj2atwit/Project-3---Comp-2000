import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Route {
	
	private static int PASSENGER_LIMIT = 6;
	
	/*
	 * Shows all given stations names and passengers.
	 */
	public static void main(String[] args) {
		try {
			//Sets up scanner for the given text file.
			File stationNamesFile = new File("src\\orange.txt");
			Scanner in = new Scanner(System.in);
			in.close();
			in = new Scanner(stationNamesFile);
			
			//Sets given station names into an array.
			Station[] stationsArr = new Station[17];
			for(int i = 0; i < stationsArr.length; i++) {
				stationsArr[i] = (new Station(in.nextLine()));
			}
			
			setPassengers(stationsArr);
			printAll(stationsArr);
			
			in.close();
			
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find Orange.txt");
		}
		
	}
	
	/*
	 * Prints all stations and all of their passenger's ID and Destination information.
	 */
	public static void printAll(Station[] stations){
		for(int i = 0; i < stations.length; i++) {
			System.out.printf("-----------------------%n%s%n Passengers:%n%n",stations[i].getName());
			//Prints all passengers, printing none only if there are no passengers at that station.
			if(stations[i].isHasPassengers()) {
				Passenger pass = stations[i].getPassengers().dequeue();
				
				//Prints out passengers until there are none left.
				while(pass != null) {
					System.out.printf("%s, %s%n", pass.getID(), pass.getDestination().getName());
					try {
						pass = stations[i].getPassengers().dequeue();
					}catch(Exception e){
						pass = null;
					}
					
				}
			} else {
				System.out.printf("None%n");
			}
			System.out.printf("========================%n%n");
		}
	}
	
	/*
	 * Sets a random amount of unique passengers to each station.
	 */
	public static void setPassengers(Station[] stationsArr) {
		Random rand = new Random();
		ArrayList<Passenger> allPassengers = new ArrayList<Passenger>();
		
		//sets a random amount of passengers to each station.
		for(int i = 0; i < stationsArr.length; i++) {
			int passAmnt = rand.nextInt(PASSENGER_LIMIT);
			for(int j = 1; j <= passAmnt; j++) {
				stationsArr[i].addPassenger(randUniquePassenger(stationsArr, i, rand, allPassengers));
				stationsArr[i].setHasPassengers(true);
			}
		}
	}
	
	/*
	 * Creates a passenger that has a unique ID code and a random station as their destination.
	 * Destination will not be the current station.
	 */
	public static Passenger randUniquePassenger(Station[] stationsArr, int i, Random rand, ArrayList<Passenger> allPassengers) {
		Passenger pass = new Passenger();
		
		//Sets random destination for the passenger
		Station dest = randDest(rand, i, stationsArr);
		pass.setDestination(dest);
		
		//Generates a random unique Passenger ID for the passenger.
		pass.randomID();
		
		//Makes sure given ID is not the same as any previously given ID.
		boolean copy = true;
		while(allPassengers.size() != 0 && copy) {
			for(int k = 0; k < allPassengers.size(); k++) {
				if(pass.getID().equals(allPassengers.get(k).getID())) {
					copy = true;
					pass.randomID();
					break;
				}else {
					copy = false;
				}
			}
		}
		
		return pass;
	}
	
	/*
	 * Gets a random destination that is not the current station
	 */
	public static Station randDest(Random rand, int i, Station[] stationsArr) {
		int destIndex = -1;
		while(destIndex != i && destIndex < 0) {
			destIndex = rand.nextInt(stationsArr.length);
		}
		return stationsArr[destIndex];
	}
	
	
}
