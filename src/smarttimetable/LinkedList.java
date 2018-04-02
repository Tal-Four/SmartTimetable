package smarttimetable;

/**
 *
 * @author AdamPlatt
 */
public class LinkedList {

    private Node rootNode;

    /**
     * Sets the root node to nothing
     *
     */
    public LinkedList() {
        this.clear();
    }

    /**
     * Adds a node with user specified data to the list
     *
     * @param data
     */
    public void addNode(int data) {
        Node newNode = new Node(data, null);
        //If no root node it makes this node the root node
        if (this.rootNode == null) {
            this.rootNode = newNode;
        } else {
            //Otherwise it is added to the end of the list
            Node currentNode = this.rootNode;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
    }

    /**
     * Deletes a node with the specified data
     *
     * @param dataToDelete
     */
    public void deleteNode(int dataToDelete) {
        boolean deleted;
        Node currentNode = this.rootNode, nodeToDelete;
        //Checks to see if the data in the node is the data being searched for
        if (currentNode.getData() == dataToDelete) {
            this.rootNode = currentNode.getNextNode();
            deleted = true;
        } else {
            while (currentNode.getNextNode().getData() != dataToDelete) {
                currentNode = currentNode.getNextNode();
            }
            nodeToDelete = currentNode.getNextNode();
            currentNode.setNextNode(nodeToDelete.getNextNode());
            deleted = true;
        }
        if (deleted) {
            System.out.println("Deleting " + dataToDelete + " from the list.");
        } else {
            // If not actually in the list.
            System.out.println(dataToDelete + " not in list.");
        }
    }

    /**
     * Returns the number of nodes in the list
     *
     * @return
     */
    public int length() {
        if (this.rootNode == null) {
            System.out.println("List empty.");
            return 0;
        }

        Node currentNode = this.rootNode;
        int counter = 1;

        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            counter++;
        }
        return counter;
    }

    /**
     * Returns the data at a user specified location
     *
     * @param location
     * @return
     */
    public int getDataAt(int location) {
        Node currentNode = this.rootNode;

        if (currentNode == null || location >= length()) {
            return 0;
        }

        for (int count = 0; count < location; count++) {
            currentNode = currentNode.getNextNode();
        }

        return currentNode.getData();
    }

    /**
     * Empties the list by setting the root node to null
     */
    public final void clear() {
        this.rootNode = null;
    }
}
