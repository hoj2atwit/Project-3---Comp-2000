
public interface QueueInterface<T> {
	
	public void enqueue(T newEntry);
	// Throws IllegalStateException
	
	public T dequeue();
	// throws NoSuchElementException
	
	public T getFront();
	// throws NoSuchElementException
	
	public boolean isEmpty();
	
	public void clear();
	
	

}
