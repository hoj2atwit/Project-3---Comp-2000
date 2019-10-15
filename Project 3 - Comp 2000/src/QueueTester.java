
public class QueueTester {

	public static void main(String[] args) {
		LinkedQueue<Integer> testQ = new LinkedQueue();
		System.out.println("-----------------------------------");
		System.out.printf("Filling queue with numbers from 0 to 9%n");
		for (int i = 0; i < 10; i++) {
			testQ.enqueue(i);
		}
		System.out.println("isEmpty should return false");
		System.out.println(testQ.isEmpty());
		System.out.println("-----------------------------------");
		System.out.println("Will call for getFront and then dequeue; both should be the same, starting from 0");
		for (int i = 0; i < 10; i++) {
			System.out.println(testQ.getFront());
			System.out.println(testQ.dequeue());
		}
		System.out.println("isEmpty should return true");
		System.out.println(testQ.isEmpty());
		System.out.println("-----------------------------------");
		System.out.printf("Filling queue with numbers from 0 to 9%n");
		for (int i = 0; i < 10; i++) {
			testQ.enqueue(i);
		}
		System.out.println("isEmpty should return false");
		System.out.println(testQ.isEmpty());
		System.out.println("-----------------------------------");
		System.out.println("Clearing queue");
		testQ.clear();
		System.out.println("isEmpty should return true");
		System.out.println(testQ.isEmpty());

	}

}
