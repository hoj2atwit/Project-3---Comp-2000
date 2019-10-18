import java.util.ArrayList;

public class Train {
	private Station[] route;
	private ArrayQueue<Passenger> passengers;
	private Station currentStation;
	private int currentStationIndex;
	private String name;
	private ArrayList<Station> passed;
	private int trainPassLimit = 30;
	
	public Train(String name, Station[] route, ArrayQueue<Passenger> passengers) {
		this.setRoute(route);
		this.setPassengers(passengers);
		setCurrentStation(route[0]);
		setPassed(new ArrayList<Station>());
		setCurrentStationIndex(0);
		this.name = name;
	}
	
	public Train(String name, Station[] route) {
		this(name, route, new ArrayQueue<Passenger>());
	}
	
	public Train(Station[] route) {
		this("Default Train", route);
	}
	
	public void flipRoute() {
		Station[] newRoute = new Station[route.length];
		int counter = 0;
		for(int i = route.length-1; i >= 0; i--) {
			newRoute[counter] = route[i];
			counter++;
		}
		route = newRoute;
	}
	
	public void goNext() {
		if(currentStationIndex != route.length-1) {
			currentStationIndex++;
			passed.add(currentStation);
			setCurrentStation(route[currentStationIndex]);
		}else {
			currentStationIndex = 1;
			flipRoute();
			passed.clear();
			setCurrentStation(route[currentStationIndex]);
		}
			
		
	}
	
	public ArrayQueue<Passenger> pickUp() {
		ArrayQueue<Passenger> pickedUp = new ArrayQueue<Passenger>();
		if(!currentStation.getPassengers().isEmpty()) {
			Passenger front = null;
			while((!(currentStation.getPassengers().isEmpty()) && (currentStation.getPassengers().getFront() != front))) {
				boolean first = false;
				
				if(front == null) {
					front = currentStation.getPassengers().getFront();
					first = true;
				}
				
				if(!currentStation.getPassengers().isEmpty()) {
					boolean notEntering = false;
					for(int i = 0; i < passed.size(); i++) {
						if(passed.contains(currentStation.getPassengers().getFront().getDestination())) {
							notEntering = true;
						}
					}
					if(notEntering) {
						currentStation.getPassengers().enqueue(currentStation.getPassengers().dequeue());
					}else {
						Passenger pass = currentStation.getPassengers().dequeue();
						passengers.enqueue(pass);
						pickedUp.enqueue(pass);
						if(first) {
							front = null;
						}
					}
					
				}
			}
		}
		return pickedUp;
	}
	
	public  ArrayQueue<Passenger> dropOff() {
		ArrayQueue<Passenger> droppedOff = new ArrayQueue<Passenger>();
		if(!passengers.isEmpty()) {
			Passenger frontPassenger = null;
			
			//Checks through passengers if not empty and if it is not the first station, or if theres a new front passenger.
			while((!(passengers.isEmpty()) && (currentStationIndex != 0) && (frontPassenger != passengers.getFront()))) {
				boolean first = false;
				
				//Sets a new front passenger if it hasnt been set yet.
				if(frontPassenger == null) {
					frontPassenger = passengers.getFront();
					first = true;
				}
				
				//Removes passenger if they are need to get off at current station.
				if(passengers.getFront().getDestination().getName().equals(currentStation.getName())) {
					droppedOff.enqueue(passengers.dequeue());
					
					//Sets the next passenger null if the top most left the queue
					if(first) {
						frontPassenger = null;
					}
				}else {
					passengers.enqueue(passengers.dequeue());
				}
				
			}
		}
		return droppedOff;
	}
	

	public Station getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

	public ArrayQueue<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayQueue<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Station[] getRoute() {
		return route;
	}

	public void setRoute(Station[] route) {
		this.route = route;
	}

	public ArrayList<Station> getPassed() {
		return passed;
	}

	public void setPassed(ArrayList<Station> arrayList) {
		this.passed = arrayList;
	}
	
	public String toString() {
		String s = "";
		s += String.format("-----------------------%nTrain: %s%nCurrentStation: %s%n%nAll Current Passengers:%n", name, currentStation.getName());
		if(!passengers.isEmpty()) {
			Passenger front = passengers.getFront();
			s += String.format("     %s", front);
			passengers.enqueue(front);
			passengers.dequeue();
			while(passengers.getFront() != front) {
				s += String.format("     %s", passengers.getFront());
				passengers.enqueue(passengers.dequeue());
			}
		}else {
			s += String.format("     none%n%n");
		}
		s+= String.format("========================%n%n");
		return s;
	}

	public int getCurrentStationIndex() {
		return currentStationIndex;
	}

	public void setCurrentStationIndex(int currentStationIndex) {
		this.currentStationIndex = currentStationIndex;
	}

	public int getTrainPassLimit() {
		return trainPassLimit;
	}

	public void setTrainPassLimit(int trainPassLimit) {
		this.trainPassLimit = trainPassLimit;
	}
	
	

}
