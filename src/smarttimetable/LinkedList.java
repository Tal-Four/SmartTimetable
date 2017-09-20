package smarttimetable;

public class LinkedList {

    // Attributes
    private Node rootNode;

    // Constructor
    public LinkedList() {
        this.rootNode = null;
    }

    // Methods
    public void addNode(Object data) {
        if (this.rootNode == null) {
            Node newNode = new Node(data, null);
            this.rootNode = newNode;
        } else {
            Node currentNode = this.rootNode;
            while (currentNode.getNextNode() != null) {
                currentNode = currentNode.getNextNode();
            }
            Node newNode = new Node(data, null);
            currentNode.setNextNode(newNode);
        }
        System.out.println("Adding " + data + " to the list:");

    }

    public void deleteNode(Object dataToDelete) {
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
    
    /*TODO
    public Object getDataAt(int location){
        
    }*/

}
