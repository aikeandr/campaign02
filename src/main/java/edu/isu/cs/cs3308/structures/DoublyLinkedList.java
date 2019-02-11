package edu.isu.cs.cs3308.structures;

/**
 * An implementation of a list using sentinel nodes as the head and tail of the
 * DLL. Each node has a previous and next node value.
 *
 * @author Andrew Aikens
 * @param <E> Element Type
 */
public class DoublyLinkedList<E> implements List<E> {

    /**
     * A node which keeps track of the previous and next node.
     *
     * @param <E> Element Type
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> previous;
        private Node(E e, Node<E> previous, Node<E> next){
            this.data = e;
            this.previous = previous;
            this.next = next;
        }
        private E getData(){
            return data;
        }
        private Node<E> getPrevious(){
            return previous;
        }
        private Node<E> getNext(){
            return next;
        }
        private void setPrev(Node<E> prev){
            previous = prev;
        }
        private void setNext(Node<E> next){
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    /**
     * Instantiates an empty DLL where head points to tail but has no previous.
     * tail points to head but has no next.
     *
     */
    public DoublyLinkedList(){
        head = new Node<>(null,null,null);
        tail = new Node<>(null, head, null);
        head.setNext(tail);
    }

    /**
     * @return first element in the list or null if the list is empty.
     */
    @Override
    public E first() {
        if(isEmpty())
            return null;
        return head.getNext().getData();
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @Override
    public E last() {
        if(isEmpty())
            return null;
        return tail.getPrevious().getData();
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     */
    @Override
    public void addLast(E element) {
        if(element == null)
            return;
        addBetween(element, tail.getPrevious(), tail);
    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list.
     */
    @Override
    public void addFirst(E element) {
        if(element == null)
            return;
        addBetween(element, head, head.getNext());
    }

    /**
     * Creates a node with given parameters and adds it between the
     * two given nodes.
     *
     * @param e element to insert
     * @param prev previous node
     * @param nxt next node
     */
    private void addBetween(E e, Node<E> prev, Node<E> nxt){
        Node<E> node = new Node<>(e, prev, nxt);
        prev.setNext(node);
        nxt.setPrev(node);
        size++;
    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list, or null if the list is empty.
     */
    @Override
    public E removeFirst() {
        if(isEmpty())
            return null;
        return remove(head.getNext());
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list, or null if the list is empty.
     */
    @Override
    public E removeLast() {
        if(isEmpty())
            return null;
        return remove(tail.getPrevious());
    }

    /**
     * Removes a given node from the list.
     *
     * @param node The node to be removed.
     * @return data of the node being removed
     */
    private E remove(Node<E> node) {
        Node<E> prev = node.getPrevious();
        Node<E> nxt = node.getNext();
        prev.setNext(nxt);
        nxt.setPrev(prev);
        size--;
        return node.getData();
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    @Override
    public void insert(E element, int index) {
        if(element == null || index < 0)
            return;
        if(index >= size()){
            addBetween(element, tail.getPrevious(), tail);
            return;
        }
        Node<E> temp = head.getNext();
        for(int i = 0; i < index; i++)
            temp = temp.getNext();
        addBetween(element, temp.getPrevious(), temp);
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the size of the list or less than 0.
     */
    @Override
    public E remove(int index) {
        if(index < 0 || index >= size())
            return null;
        Node<E> temp = head.getNext();
        for(int i = 0; i < index; i++)
            temp = temp.getNext();
        return remove(temp);
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    @Override
    public E get(int index) {
        if(index < 0 || index >= size())
            return null;
        Node<E> temp = head.getNext();
        for(int i = 0; i < index; i++)
            temp = temp.getNext();
        return temp.getData();
    }

    /**
     * @return The current size of the list. Note that 0 is returned for an
     * empty list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Prints the list from first to last with each element on a new line.
     */
    @Override
    public void printList() {
        if(isEmpty())
            return;
        Node<E> tempNode = head.getNext();
        for(int i=0;i<size;i++){
            System.out.println(tempNode.getData());
            tempNode = tempNode.getNext();
        }
    }
}