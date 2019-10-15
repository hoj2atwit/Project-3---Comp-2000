
public class ArrayQueue<T> implements QueueInterface<T> {
	
	private T[] queueArray;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 10;
	private int frontIndex;
	private int backIndex;
	
	/**
	 * Constructor for capacity input; sets queue capacity to input
	 * @param capacity
	 */
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		queueArray = (T[])new Object[this.capacity];
		frontIndex = 0;
		backIndex = this.capacity-1;
	}
	
	/**
	 * Constructor for no inputs; Sets queue capacity to default 10
	 */
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Adds element to the end of the array
	 */
	@Override
	public void enqueue(T newEntry) {
		backIndex = (backIndex + 1) % capacity;
		queueArray[backIndex] = newEntry;
		ensureCapacity();
	}

	/**
	 * Removes element from front of array and returns the data
	 */
	@Override
	public T dequeue() {
		T output = null;
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		output = queueArray[frontIndex];
		queueArray[frontIndex] = null;
		frontIndex = (frontIndex + 1)%capacity;
		if (isEmpty()) {
			frontIndex = 0; 
			backIndex = capacity - 1;
		}
		return output;
	}

	/**
	 * Returns front element of queue if queue has an element; null if empty
	 */
	@Override
	public T getFront() {
		T output;
		if (!isEmpty()) {
			output = queueArray[frontIndex];
		} else {
			System.out.printf("getFront failed; Queue is empty!%n");
			return null;
		}
		return output;
	}

	/**
	 * Returns boolean for if queue is empty or not
	 */
	@Override
	public boolean isEmpty() {
		if ((frontIndex == (backIndex + 1)%capacity) && queueArray[frontIndex] == null) {
			return true;
		}
		return false;
	}

	/**
	 * Dequeues until queue is empty
	 */
	@Override
	public void clear() {
		while (!isEmpty()){
			dequeue();
		}
	}
	
	/**
	 * Doubles capacity when full
	 */
	private void ensureCapacity() {
		capacity *= 2;
		T[]temp = (T[]) new Object[capacity];
		int size = (backIndex + capacity - frontIndex + 1)%capacity;
		for (int i = 0; i < size; i++) {
			temp[i] = queueArray[(frontIndex + i)%capacity];
			queueArray = temp;
		}
		frontIndex = 0;
		backIndex = size-1;
	}

}
