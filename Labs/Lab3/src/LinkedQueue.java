/**
 * Created by johnjudge on 2/17/15.
 */
public class LinkedQueue <T extends Prioritizable> implements PriorityQueue<T> {

    private Node<T> head;
    //private T head = null;
    private int queueSize = 0;

    public LinkedQueue() {

    }
    public boolean isEmpty() {
        if(queueSize == 0){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {

    }

    /**
     * Inserts given node into the queue in the proper position based on     coolness number
     */
    public void insert(T toInsert) {
        Node<T> tempNode = (Node<T>) toInsert;
        // Create an int w/ the node's coolness

        Node<T> currentNode = (Node<T>) head;
        while(currentNode.getNext() != null){
            //loop like above except by "coolness" | while (     currentNode.getCoolness < currentNode.getNext.getCoolness)
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(tempNode);
        queueSize++;
    }

    /**
     * Removes the head element from the queue
     * @return
     */
    public T dequeue() {
        Node<T> currentNode = (Node<T>) head;
        if(head != null){
            //currentNode.poll();
            //currentNode = currentNode.getNext;
        }
        queueSize--;
        return (T) currentNode;
    }
}
