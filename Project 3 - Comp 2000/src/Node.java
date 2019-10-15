
public class Node<T> {
	private T data;
	private Node next;
	
	public Node (T data, Node nextNode) {
		this.data = data;
		next = nextNode;
	}
	
	public Node (T data) {
		this(data, null);
	}
	
	public T getData() {
		return data;
	}
	
	public void setData (T newData) {
		data = newData;
	}
	
	public Node getNext () {
		return next;
	}
	
	public void setNext (Node nextNode) {
		next = nextNode;
	}
	
	public Node copyData() {
		return new Node (data);
	}
}
