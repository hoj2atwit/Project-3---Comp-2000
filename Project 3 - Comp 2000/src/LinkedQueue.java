
public class LinkedQueue<T> implements QueueInterface<T>{
	
	private Node<T> frontNode;
	private Node<T> backNode;
	
	public LinkedQueue() {
		frontNode = null;
		backNode = null;
	}

	/**
	 * Method for adding an entry to the end of the LinkedQueue
	 */
	@Override
	public void enqueue(T newEntry) {
		Node<T> newNode = new Node<>(newEntry);
		// If queue is empty, sets entry as the front node
		if (isEmpty()) {
			frontNode = newNode;
		} else {
			// If queue is not empty, sets the node after backNode as entry
			backNode.setNext(newNode);
		}
		// Sets the back node as the new entry
		backNode = newNode;	
	}

	/**
	 * Method for removing the front entry from the LinkedQueue
	 */
	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new EmptyQueueException("The queue is empty!");
		}
		T result = frontNode.getData();
		frontNode = frontNode.getNext();
		if (frontNode == null) {
			backNode = null;
		}
		return result;
	}

	/**
	 * Returns data of front node; if queue is empty, returns null
	 */
	@Override
	public T getFront() {
		T data = null;
		if (!isEmpty()) {
			data = frontNode.getData();
		}
		return data;
	}

	@Override
	public boolean isEmpty() {
		boolean booleanEmpty = false;
		if (frontNode == null) {
			booleanEmpty = true;
		}
		return booleanEmpty;
	}

	@Override
	public void clear() {
		while (!isEmpty()) {
			dequeue();
		}
		
	}

}
