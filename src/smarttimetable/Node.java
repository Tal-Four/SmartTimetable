package smarttimetable;

/**
 * A class used in the LinkedList class to store an ID and the position of the
 * next node in the list.
 *
 * @author AdamPlatt
 */
public class Node {

    //The data stored in the node (IE an ID)
    private int data;
    //The next node in the linked list
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
