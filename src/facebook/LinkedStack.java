package facebook;

import java.util.EmptyStackException;



public final class LinkedStack<T> implements StackInterface<T>
{

    private Node topNode; // References the first node in the chain

    //default constructor
    public LinkedStack() {
        topNode = null;
    }

    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }
    public T peek() {
        //checkInitialization();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData;
    }//end peek

    public T pop() {
        T top = peek(); //Might throw EmptyStackException
        assert topNode != null;
        topNode = topNode.getNextNode();

        return top;
    }//end pop

    public boolean isEmpty() {
        return topNode == null;
    }// end isEmpty

    public void clear () {
        topNode = null;
    }//end clear




    private class Node {
        public T getData;
        private T data;
        private Node next;

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

    } //end Node

}// end LinkedStack

