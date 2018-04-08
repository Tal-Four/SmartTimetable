package smarttimetable;

/**
 * A series of nodes used to store the list of item IDs that are being viewed.
 *
 * @author AdamPlatt
 */
public class LinkedList {

    //The node that the linked list starts at
    private Node rootNode;

    /**
     * Creates an empty linked list
     *
     */
    public LinkedList() {
        //Sets the root node to null
        this.clear();
    }

    /**
     * Adds a node with user specified data to the list
     *
     * @param data
     */
    public void addNode(int data) {
        //Creates a new node object that contains the data to be stored
        Node newNode = new Node(data, null);
        //If no root node it makes this node the root node
        if (this.rootNode == null) {
            this.rootNode = newNode;
        } else {
            //Otherwise it is added to the end of the list
            Node currentNode = this.rootNode;
            //Looping through nodes until a node with no link is found (IE the last node)
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            //Sets the end node to link to the new node
            currentNode.setNextNode(newNode);
        }
    }

    /**
     * Deletes a node with the specified data
     *
     * @param dataToDelete
     */
    public void deleteNode(int dataToDelete) {
        //Creating new node objects
        Node nodeToDelete, currentNode = this.rootNode;
        //Checks to see if the data in the current node (IE the root node) is the data being searched for
        if (currentNode.getData() == dataToDelete) {
            //Sets the root node to be the root node's next node, therefore removing the old root node
            this.rootNode = currentNode.getNextNode();
        } else {
            //Looping through the nodes until it finds a node which links to the node with the data to be deleted
            while (currentNode.getNextNode().getData() != dataToDelete) {
                currentNode = currentNode.getNextNode();
            }
            nodeToDelete = currentNode.getNextNode();
            /**
             * Removing the link to the node to be deleted by setting the node
             * that links to the node to be deleted to link to the node that the
             * node to be deleted links to, therefore removing the node to be
             * deleted from the chain.
             *
             */
            currentNode.setNextNode(nodeToDelete.getNextNode());
        }
    }

    /**
     * Returns the number of nodes in the list
     *
     * @return
     */
    public int length() {
        //If there is no root node then the link has 0 nodes in it
        if (this.rootNode == null) {
            return 0;
        }

        Node currentNode = this.rootNode;
        int counter = 1;

        //Looping through nodes until the end node is found
        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
            counter++;
        }
        //Returning the number of nodes in the list
        return counter;
    }

    /**
     * Returns the data at a specified location
     *
     * @param location
     * @return
     */
    public int getDataAt(int location) {
        Node currentNode = this.rootNode;

        //Checks to see if the location is within the list
        if (currentNode == null || location >= length()) {
            return 0;
        }

        //Looping through to find the node referenced by the location
        for (int count = 0; count < location; count++) {
            currentNode = currentNode.getNextNode();
        }
        //Returning the data at the referenced position
        return currentNode.getData();
    }

    /**
     * Empties the list by setting the root node to null
     */
    public final void clear() {
        this.rootNode = null;
    }
}
