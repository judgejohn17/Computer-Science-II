/**
 * Created by johnjudge on 2/17/15.
 */
public class Node<T> {
    /**
     * This field holds the value.
     */
    private T data;
    /**
     * This field connects this Node to the next one.
     */
    private Node<T> next;

    /**
     * Initialize a new node.
     *
     * @param data the data value to be stored in the node
     * @param next the successor to this node, or null if none
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Accessor for link.
     *
     * @return the successor to this node, or null if none
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Mutator for link.
     *
     * @param next new successor for this node, or null if this
     *             is now the last node of the list
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Accessor for value.
     *
     * @return the value stored in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Mutator for value.
     *
     * @param data new value to be stored in this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * A string representation of this node.
     * Note that toString will be called on the successor,
     * so a long linked list will produce a large string.
     */
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}