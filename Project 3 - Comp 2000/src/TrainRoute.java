import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TrainRoute {
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter textWriter = new PrintWriter("TrainTest.txt");
		Route route = new Route();
		route.setRandomPassengers();
		Train train1 = new Train("Forward Train", route.getStationsArr());
		Train train2 = new Train("Backwards Train", route.getReverseRoute());
		textWriter.println(route);
		textWriter.println(train1 + "%n" + train2);
		
		for(int i = 0; i < route.getNumStations(); i++) {
			textWriter = new PrintWriter("TrainTest.txt");
			System.out.printf("Printing step %d%n", i);
			train1.pickUpStation();
			train1.dropOff();
			textWriter.println(train1);
			train1.goNext();
			textWriter.println(train1);
			train2.pickUpStation();
			train2.dropOff();
			textWriter.println(train2);
			train2.goNext();
			textWriter.println(train2);
			textWriter.close();
		}
		
		textWriter = new PrintWriter("TrainTest.txt");
		textWriter.println(route);
		textWriter.close();
		System.out.printf("Done.");
	}
}
