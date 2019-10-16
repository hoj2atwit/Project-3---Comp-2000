import java.util.Random;

public class Passenger {
	private String ID;
	private Station destination;

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
	}
	
	public void randomID() {
		Random rand = new Random();
		int[] IDArr = new int[5];
		for(int i = 0; i < IDArr.length; i++) {
			IDArr[i] = rand.nextInt(10);
		}
		
		setID(String.format("%d%d%d%d%d",IDArr[0], IDArr[1], IDArr[2], IDArr[3], IDArr[4]));
		
	}
	
	public String toString() {
		String s = "";
		s += String.format("%s, %s%n", getID(), getDestination().getName());
		return s;
	}
	

}
