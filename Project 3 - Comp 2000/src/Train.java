import java.util.ArrayList;

public class Train {
	private Station[] route;
	private LinkedQueue<Passenger> passengers;
	private Station currentStation;
	private int currentStationIndex;
	private String name;
	private ArrayList<Station> passed;
	
	public Train(String name, Station[] route, LinkedQueue<Passenger> passengers) {
		this.setRoute(route);
		this.setPassengers(passengers);
		setCurrentStation(route[0]);
		setPassed(new ArrayList<Station>());
		setCurrentStationIndex(0);
		this.name = name;
	}
	
	public Train(String name, Station[] route) {
		this(name, route, new LinkedQueue<Passenger>());
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
	
	public void pickUpStation() {
		if(!currentStation.getPassengers().isEmpty()) {
			Passenger front = null;
			while(!currentStation.getPassengers().isEmpty() && (currentStation.getPassengers().getFront() != front)) {
				if(front == null) {
					front = currentStation.getPassengers().getFront();
				}
				if(!currentStation.getPassengers().isEmpty()) {
					boolean notEntering = false;
					for(int i = 0; i < passed.size(); i++) {
						if(passed.contains(currentStation.getPassengers().getFront().getDestination())) {
							notEntering = true;
						}
					}
					if(notEntering) {
						currentStation.getPassengers().enqueue(currentStation.getPassengers().getFront());
						currentStation.getPassengers().dequeue();
					}else {
						passengers.enqueue(currentStation.getPassengers().getFront());
						currentStation.getPassengers().dequeue();
					}
				}
			}
		}
	}
	
	public void dropOff() {
		if(!passengers.isEmpty()) {
			Passenger frontPassenger = null;
			while(!passengers.isEmpty() && (passengers.getFront() != frontPassenger) && (currentStationIndex != 0)) {
				if(frontPassenger == null) {
					frontPassenger = passengers.getFront();
				}
				if(passengers.getFront().getDestination().getName().equals(currentStation.getName())) {
					passengers.dequeue();
					if(passengers.getFront().equals(frontPassenger)) {
						frontPassenger = passengers.getFront();
					}
				}else {
					passengers.enqueue(passengers.getFront());
					passengers.dequeue();
				}
			}
		}
	}
	

	public Station getCurrentStation() {
		return currentStation;
	}

	public void setCurrentStation(Station currentStation) {
		this.currentStation = currentStation;
	}

	public LinkedQueue<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(LinkedQueue<Passenger> passengers) {
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
		s += String.format("-----------------------%nTrain: %s%nCurrentStation: %s%n%n", name, currentStation.getName());
		if(!passengers.isEmpty()) {
			Passenger front = passengers.getFront();
			s += String.format("%s", front);
			passengers.enqueue(front);
			passengers.dequeue();
			while(passengers.getFront() != front) {
				s += String.format("%s", passengers.getFront());
				passengers.enqueue(passengers.getFront());
				passengers.dequeue();
			}
		}else {
			s += String.format("none%n%n");
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
	
	

}
