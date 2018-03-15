package smarttimetable;

public class LinkedList {

    private Node rootNode;

    //Sets the root node to nothing
    public LinkedList() {
        this.clear();
    }

    //Adds a node with user speciifed data to the list
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

    //Deletes a node with the specified data
    public void deleteNode(int dataToDelete) {
        boolean deleted = false;
        Node currentNode = this.rootNode, nodeToDelete;
        if (currentNode.getData() == dataToDelete) {
            this.rootNode = currentNode.getNextNode();
            currentNode = null;
            deleted = true;
        } else {
            while (currentNode.getNextNode().getData() != dataToDelete) {
                currentNode = currentNode.getNextNode();
            }
            nodeToDelete = currentNode.getNextNode();
            currentNode.setNextNode(nodeToDelete.getNextNode());
            nodeToDelete = null;
            deleted = true;
        }
        if (deleted) {
            System.out.println("Deleting " + dataToDelete + " from the list.");
        } else {
            // If not actually in the list.
            System.out.println(dataToDelete + " not in list.");
        }
    }

    //Prints the entire list to the console
    public void printList() {

        if (this.rootNode == null) {
            System.out.println("List empty.");
            return;
        }

        Node currentNode = this.rootNode;

        System.out.println(currentNode.getData());

        while (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();

            System.out.println(currentNode.getData());
        }
    }

    //Returns the number of nodes in the list
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

    //Returns the data at a user specified location
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
    
    public void clear(){
        this.rootNode = null;
    }
}
