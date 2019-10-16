import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TrainRoute {
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter textWriter1 = new PrintWriter("Train1Test.txt");
		PrintWriter textWriter2 = new PrintWriter("Train2Test.txt");
		Route route = new Route();
		route.setRandomPassengers();
		Train train1 = new Train("Forward Train", route.getStationsArr());
		Train train2 = new Train("Backwards Train", route.getReverseRoute());
		
		textWriter1.println(route);
		textWriter2.println(route);
		textWriter1.flush();
		textWriter2.flush();
		textWriter1.println(train1);
		textWriter2.println(train2);
		
		for(int i = 0; i < route.getNumStations(); i++) {
			System.out.printf("Printing step %d%n", i);
			
			train1.pickUpStation();
			train1.dropOff();
			textWriter1.println(train1);
			train1.goNext();
			textWriter1.println(train1);
			
			train2.pickUpStation();
			train2.dropOff();
			textWriter2.println(train2);
			train2.goNext();
			textWriter2.println(train2);
			
			if(i!=0) {
				if(i%3 == 0) {
					textWriter1.flush();
					textWriter2.flush();
				}
				if(i % 5 == 0) {
					route.setRandomPassengers();
					textWriter1.println(route);
					textWriter2.println(route);
					textWriter1.flush();
					textWriter2.flush();
				}
			}
		}
		
		textWriter1.println(route);
		textWriter2.println(route);
		textWriter1.close();
		textWriter2.close();
		System.out.printf("Done.");
	}
}
