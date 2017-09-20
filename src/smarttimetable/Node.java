package smarttimetable;

public class Node {

	private Object data;
	private Node nextNode;

	//Creates a node
	public Node(Object data, Node nextNode) {
		this.data = data;
		this.nextNode = nextNode;
	}

	//Getters/Setters
	public Object getData() {
		return this.data;
	}
	
	public Node getNextNode() {
		return this.nextNode;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}
