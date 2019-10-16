
public class Station {
	private String name;
	private LinkedQueue<Passenger> passengers;
	
	public Station(String name, LinkedQueue<Passenger> passengers) {
		this.name = name;
		this.setPassengers(passengers);
	}	
	
	public Station(String name) {
		this(name, new LinkedQueue<Passenger>());
	}
	
	public void addPassenger(Passenger p) {
		passengers.enqueue(p);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public LinkedQueue<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(LinkedQueue<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	
}
