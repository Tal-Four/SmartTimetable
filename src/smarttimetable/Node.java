package smarttimetable;

/**
 *
 * @author AdamPlatt
 */
public class Node {

    private int data;
    private Node nextNode;

    /**
     * Creates a node
     *
     * @param data
     * @param nextNode
     */
    public Node(int data, Node nextNode) {
        this.data = data;
        this.nextNode = nextNode;
    }

    //Getters/Setters
    public int getData() {
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
