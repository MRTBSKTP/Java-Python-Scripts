/**
 * A Singly Linked List implementation for understanding 
 * data structure basics. Actually a linked list class
 * exists in java collections library but again this is for
 * understanding data structure basics.
 * 
 * 1 - class Node: Represents individual nodes of
 *                 a linked list. 
 * 
 * 2 - class LinkedList: Structured from back to back
 *                       nodes
 * 
 *   Visualization of implemeted structure is as follows:
 * 
 *                            - Linked List -
 *                                    
 *    starts                                                       ends
 *   [ at a --------->|Node , next--------->|Node , next---------> at a ]
 *     node            (thisNode)             (nextNode)           node
 *
 *   Node object instances hold assigned node data and next nodes
 *   reference. Every node but last points to another node this helps
 *   adding new elements and traversing along linked list.
 * 
 *   Mert Beşiktepe 2019 ITU 
 */

/**
 * LinkedList class implementation
 * 
 * A small graphic example for intutive purposes:
 * 
 * Node(1)             Node(2)             Node(3)             Node(4)                                                                         
 * [Data=1      ]      [Data=2      ]      [Data=3      ]      [Data=4      ]                                                                     
 * [next=Node(2)]----->[next=Node(3)]----->[next=Node(4)]----->[next=  null ]                                                     
 *  {head node}                                                 {last node}   
 * 
 * head node : variable helps us to determine if the list is empty and 
 *             traverse along list because there exist no info about 
 *             previous node in the list. This causes forward motion
 *             to be the only way of traversing list.
 * 
 * last node : describes the end of the list.
 * 
 */
public class LinkedList {

    // class variables
    Node headNode;

    /**
    *  Node class implementation as an inner class
    */
    static class Node {

        // class variables
        String nodeData;
        Node nextNode ;   

        /*
        class constructor
        
        Constructing simple nodes from supplied point id's.
        Only one node at a time can be inserted to list with
        unknown next pointer. 
        
        input: 
            thisNodeID 
        */
        Node (String thisNodeID) {
            
            // assigning the variables
            this.nodeData = thisNodeID;
            this.nextNode = null;
        }
    }

    /*
      class constructor for linkedList

      creates a linked list instance with a head node variable 
      initialized to null.
    
    */
    public LinkedList () { 
        
        // word 'this' is redundant but helps reading the code
        this.headNode = null;
    }

    /*
      Insert method have 2 behaviors.
      If the list is currently empty then inserted node
      becomes head node. Else new node will be inserted at
      the end of the list.
      
      input:
            nodeID to create a node
     */
    public void insert(String nodeID) {
        
        // create the Node instance from supplied value
        Node newNode = new Node(nodeID);
                        // now newNode is something like this:
                        // newNode = [Data: nodeID, next=null]

        // Proceed with depending on presence of headNode
        if (this.headNode == null) {
            
            // If no headNode exists then assign newNode as head
            this.headNode = newNode;
            // end...
        } 
        else {
            
            // If a head node traverse through the list starting from
            // the headNode and append newNode at the end.
            Node currentNode = this.headNode;
            // loop till current node points to another node
            while (currentNode.nextNode != null) {

                // shift the current node to be the next node
                // loop ends when currentNode reaches to lastNode
                currentNode = currentNode.nextNode;
                /* This is visually like this for the example at class definition:
                    
                    currentNode = this.headNode                        (Node 1)
                    currentNode -> [Data=1      ]
                                   [next=Node(2)]  
                    
                    while loop:
                        1st iteration : currentNode.next != null       (Node 1)
                                        currentNode = currentNode.next (Node 2)
                                        [Data=1      ]
                                        [next=Node(2)]---> currentNode -> [Data=2      ]
                                                                          [next=Node(3)]
                        
                        2nd iteration : currentNode.next != null       (Node 2)
                                        currentNode = currentNode.next (Node 3)
                                        [Data=2      ]
                                        [next=Node(3)]---> currentNode -> [Data=3      ]
                                                                          [next=Node(4)]
                        
                        3rd iteration : currentNode.next != null       (Node 3)
                                        currentNode = currentNode.next (Node 4)
                                        [Data=3      ]
                                        [next=Node(4)]---> currentNode -> [Data=4      ]
                                                                          [next=  null ]
                        
                        4th iteration : currentNode.next == null       (Node 4)
                                        LOOP ENDS AND WE ARE AT LAST ELEMENT
                */
            }
            
            // now currentNode is lastNode. Just change the nodes 'next'
            // variable to point at newly created node
            currentNode.nextNode = newNode; 
            // end...
        }
    }
     
    /*
       A method to print linked list. 
    */
    public void printList() {
            
        // traverse through list print node by node
        // same as the one at the 'insert' method
        Node currentNode = headNode;
        while (currentNode.nextNode != null) {

            // print the node
            System.out.format("This node : %s %n", currentNode.nodeData);

            // shift the node
            currentNode = currentNode.nextNode;
        }
        
        // above loop logic excludes the last node we must print it seperately
        System.out.format("This node : %s %n", currentNode.nodeData);
        // end...
    }

    /**
     * Below is a driver code to test structures above
     */
    public static void main(String[] args) {
        
        // instaniate a LinkedList
        LinkedList myLinkedList = new LinkedList();


        // populate the list with insert method
        myLinkedList.insert("A");        
        myLinkedList.insert("B");    
        myLinkedList.insert("C");    
        myLinkedList.insert("D");    
        myLinkedList.insert("E");    

        // then print the elements
        myLinkedList.printList();
        // end...
    }
}
// EOF...
