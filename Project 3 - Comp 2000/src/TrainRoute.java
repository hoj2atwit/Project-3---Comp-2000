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
		
		textWriter1.printf("TRAIN TIME%n");
		textWriter1.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
		textWriter2.printf("TRAIN TIME%n");
		textWriter2.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
		
		textWriter1.printf("%s%n", train1);
		textWriter2.printf("%s%n", train2);
		
		for(int i = 1; i <= 100; i++) {
			System.out.printf("Printing step %d%n", i);
			
			textWriter1.printf("========================DROPPED OFF %d===========================%n", i);
			ArrayQueue<Passenger> dropped1 = train1.dropOff();
			if(!dropped1.isEmpty()) {
				while(!dropped1.isEmpty()) {
					textWriter1.printf("%s%n", dropped1.dequeue());
				}
			}else {
				textWriter1.printf("None%n");
			}
			
			
			textWriter1.printf("========================PICKED UP %d===========================%n", i);
			ArrayQueue<Passenger> picked1 = train1.pickUp();
			if(!picked1.isEmpty()) {
				while(!picked1.isEmpty()) {
					textWriter1.printf("%s", picked1.dequeue());
				}
			}else {
				textWriter1.printf("None%n");
			}
			textWriter1.printf("========================MOVE TO NEXT STOP===========================%n");
			train1.goNext();
			textWriter1.printf("%s%n", train1);
			
			
			
			textWriter2.printf("========================DROP OFF %d===========================%n", i);
			ArrayQueue<Passenger> dropped2 = train2.dropOff();
			if(!dropped2.isEmpty()) {
				while(!dropped2.isEmpty()) {
					textWriter2.printf("%s%n", dropped2.dequeue());
				}
			}else {
				textWriter2.printf("None%n");
			}
			textWriter2.printf("========================PICK UP %d===========================%n", i);
			ArrayQueue<Passenger> picked2 = train2.pickUp();
			if(!picked2.isEmpty()) {
				while(!picked2.isEmpty()) {
					textWriter2.printf("%s%n", picked2.dequeue());
				}
			}else {
				textWriter2.printf("None%n");
			}
			textWriter2.printf("========================MOVE TO NEXT STOP===========================%n");
			train2.goNext();
			textWriter2.printf("%s%n", train2);
			
			if(i!=0) {
				if(i%3 == 0) {
					textWriter1.flush();
					textWriter2.flush();
				}
				if(i % 5 == 0) {
					route.setRandomPassengers();
					textWriter1.printf("PASSENGER RESTOCK TIME%n");
					textWriter1.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
					textWriter2.printf("PASSENGER RESTOCK TIME%n");
					textWriter2.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
					textWriter1.printf("%s%n", route);
					textWriter2.printf("%s%n", route);
					textWriter1.printf("BACK TO TRAINS%n");
					textWriter1.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
					textWriter2.printf("BACK TO TRAINS%n");
					textWriter2.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++%n");
					textWriter1.flush();
					textWriter2.flush();
				}
			}
		}
		
		textWriter1.printf("%s%n", route);
		textWriter2.printf("%s%n", route);
		textWriter1.close();
		textWriter2.close();
		System.out.printf("Done.");
	}
}
