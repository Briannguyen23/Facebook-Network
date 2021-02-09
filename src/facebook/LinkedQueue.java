package facebook;

import java.util.NoSuchElementException;
public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode;
    private Node lastNode;

    public LinkedQueue() {
        firstNode = null;
        lastNode = null;
    }// end default constructor

    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry, null);
        if(isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        lastNode = newNode;

    }//end enqueue

    public T getFront() {
        if(isEmpty())
            //throw new EmptyQueueException();
            throw new NoSuchElementException();
        else
            return firstNode.getData();
    }//end getFront

    public T dequeue() {
        T front = getFront();
        assert firstNode != null;
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();

        if(firstNode == null)
            lastNode = null;
        return front;
    }//end dequeue

    public boolean isEmpty() {
        return(firstNode == null) && (lastNode == null);
    }//end isEmpty
    public void clear() {
        firstNode = null;
        lastNode = null;
    }//end clear

    public boolean checkEntry(T newEntry) {
        Node currentNode = firstNode;
        boolean matchEntry = false;

        while (currentNode != null) {
            if((currentNode.getData()).equals(newEntry))
                matchEntry = true;
            currentNode = currentNode.next;
        }
        return matchEntry;
    }//end clear

    public String toString() {
        String result = "{ ";
        Node currentNode = firstNode;
        while (currentNode != null) {

            result = result + "<" + currentNode.data + "> ";
            currentNode = currentNode.next;

        }
        result = result + "}";

        return result;
    }



    class Node{
        private T 	data;  //Entry in List
        private Node next; // LInk to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        private T getData() {
            return data;
        } //end getData

        private void setData(T newData) {
            data = newData;
        } //end setData

        private Node getNextNode() {
            return next;
        } //end getNextNode

        private void setNextNode(Node nextNode) {
            next = nextNode;
        } //end setNextNode
    } // end LinkedQueue


}
