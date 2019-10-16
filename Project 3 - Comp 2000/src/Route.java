import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Route {
	
	private static int NUM_STATIONS = 20;
	private static int PASSENGER_LIMIT = 4;
	private static Station[] stationsArr = new Station[NUM_STATIONS];
	ArrayList<Passenger> allPassengers;
	/*
	 * Shows all given stations names and passengers.
	 */
	public void setRoute() {
		try {
			//Sets up scanner for the given text file.
			File stationNamesFile = new File("src\\orange.txt");
			Scanner in = new Scanner(System.in);
			in.close();
			in = new Scanner(stationNamesFile);
			
			//Sets given station names into an array.
			for(int i = 0; i < stationsArr.length; i++) {
				stationsArr[i] = (new Station(in.nextLine()));
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			System.out.printf("Cannot find Orange.txt");
			System.exit(0);
		}
		
	}
	
	public Station[] getStationsArr() {
		return stationsArr;
	}
	
	public Station[] getReverseRoute() {
		Station[] stationsTemp = new Station[NUM_STATIONS];
		int counter = 0;
		for(int i = stationsArr.length-1; i >= 0; i--) {
			stationsTemp[counter] = stationsArr[i];
			counter++;
		}
		return stationsTemp;
	}
	
	public Route() {
		setRoute();
	}
	
	/*
	 * Prints all stations and all of their passenger's ID and Destination information.
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i < stationsArr.length; i++) {
			s += String.format("-----------------------%n%s%n Passengers:%n%n",stationsArr[i].getName());
			LinkedQueue<Passenger> passengersTemp = new LinkedQueue<Passenger>();
			//Prints all passengers, printing none only if there are no passengers at that station.
			if(!stationsArr[i].getPassengers().isEmpty()) {
				Passenger pass = stationsArr[i].getPassengers().dequeue();
				passengersTemp.enqueue(pass);
				
				//Prints out passengers until there are none left.
				while(pass != null) {
					s += String.format("%s", pass);
					try {
						pass = stationsArr[i].getPassengers().dequeue();
						passengersTemp.enqueue(pass);
					}catch(Exception e){
						pass = null;
					}
					
				}
			} else {
				s += String.format("None%n");
			}
			s += String.format("========================%n%n");
			stationsArr[i].setPassengers(passengersTemp);
		}
		return s;
	}
	
	/*
	 * Sets a random amount of unique passengers to each station.
	 */
	public void setRandomPassengers() {
		Random rand = new Random();
		allPassengers = new ArrayList<Passenger>();
		
		//sets a random amount of passengers to each station.
		for(int i = 0; i < stationsArr.length; i++) {
			int passAmnt = rand.nextInt(PASSENGER_LIMIT);
			for(int j = 1; j <= passAmnt; j++) {
				stationsArr[i].addPassenger(randUniquePassenger(i, rand));
			}
		}
	}
	
	/*
	 * Creates a passenger that has a unique ID code and a random station as their destination.
	 * Destination will not be the current station.
	 */
	public Passenger randUniquePassenger(int i, Random rand) {
		Passenger pass = new Passenger();
		
		//Sets random destination for the passenger
		Station dest = randDest(rand, i);
		while (dest == stationsArr[i]) {
			dest = randDest(rand, i);
		}
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
	public Station randDest(Random rand, int i) {
		int destIndex = -1;
		while(destIndex != i && destIndex < 0) {
			destIndex = rand.nextInt(stationsArr.length);
		}
		return stationsArr[destIndex];
	}
	
	public int getNumStations() {
		return NUM_STATIONS;
	}
	
	
}
