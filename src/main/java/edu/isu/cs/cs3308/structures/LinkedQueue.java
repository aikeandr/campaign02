package edu.isu.cs.cs3308.structures;

/**
 * A queue created using a DoublyLinkedList structure. The top of the queue is
 * the first element added to the queue and will be the first element
 * to be removed.(First IN/First OUT) New elements are added to the end of the
 * underlining DoublyLinkedList, the bottom of the queue.
 *
 * @author Andrew Aikens
 * @param <E> Element type
 */
public class LinkedQueue<E> implements Queue<E> {
    private DoublyLinkedList<E> queue;
    public LinkedQueue(){ queue = new DoublyLinkedList<>(); }

    /**
     * @return The number of elements in the queue
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * @return tests whether the queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Inserts an element at the end of the queue.
     *
     * @param element Element to be inserted.
     */
    @Override
    public void offer(E element) {
        queue.addLast(element);
    }

    /**
     * @return The value first element of the queue (with out removing it), or
     * null if empty.
     */
    @Override
    public E peek() {
        return queue.first();
    }

    /**
     * @return The value of the first element of the queue (and removes it), or
     * null if empty.
     */
    @Override
    public E poll() {
        return queue.removeFirst();
    }

    /**
     * Prints the contents of the queue starting at top, one item per line. Note
     * this method should not empty the contents of the queue.
     */
    @Override
    public void printQueue() {
        queue.printList();
    }
}