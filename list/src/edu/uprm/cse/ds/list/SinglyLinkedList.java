package edu.uprm.cse.ds.list;

import java.io.PrintStream;
import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E element; // reference to value stored in the Node
        private Node<E> next;  // reference to next Node in the chain

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public Node(){
            this.element = null;
            this.next = null;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            // this is a  Node
            ///this.next = next - next of this is the parameter
            this.next = next;
        }
    }

    // private fields of Singly Linked List

    private Node<E> header;
    private int currentSize;

    public SinglyLinkedList(){
        this.currentSize = 0;
        this.header = new Node<>();
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean isMember(E e) {
        return firstIndex(e) >= 0;
    }

    @Override
    public int firstIndex(E e) {
//        Node<E> temp = this.header.getNext();
//        int counter = 0;
//        while(temp != null){
//            if (temp.getElement().equals(e)){
//                return counter; // found it! return position kept in counter
//            }
//            counter++;
//            temp = temp.getNext();
//        }
//        return -1;
        int counter  = 0;
        for (Node<E> temp = this.header.getNext(); temp != null; temp = temp.getNext(), ++counter){
            if (temp.getElement().equals(e)){
                return counter;
            }
        }
        return -1;
    }

    @Override
    public int lastIndex(E e) {
        int counter = 0;
        int position = -1;
        for (Node<E> temp = this.header.getNext(); temp != null; temp = temp.getNext(), ++counter){
            if (temp.getElement().equals(e)){
                position = counter;
            }
        }
        return position;
    }

    @Override
    public void add(E e) {
        // Append - add at the end of the list
        if (this.isEmpty()){
            Node<E> newNode = new Node<>(); // new node to add
            newNode.setElement(e);
            // connect to the list
            this.header.setNext(newNode);
            this.currentSize++;
        }
        else {
            // Cool
            //for (Node<E> temp = header.getNext(); temp.getNext() != null; temp = temp.getNext());

            // Other alternative
            Node<E> temp = header.getNext();
            while (temp.getNext() != null){
                temp = temp.getNext();
            }
            // temp is a reference to the last node in the list
            Node<E> newNode = new Node<>();
            newNode.setElement(e);
            temp.setNext(newNode);
            this.currentSize++;
        }

    }

    @Override
    public void add(E e, int index) {
        if ((index < 0) || (index > this.size())){
            throw new IndexOutOfBoundsException("index is out range.");
        }
        else if (index == this.size()){
            this.add(e);
        }
        else {
            // what is index == 0
            Node<E> temp = null; // temp is the node before index
            if (index == 0){
                temp = this.header;
            }
            else {
                temp = this.getPosition(index - 1);
            }
            Node<E> newNode = new Node<>();
            newNode.setElement(e);
            // insertion
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            this.currentSize++;
        }
    }

    private Node<E> getPosition(int index){
        // assume that index is valid
        int currentPosition = 0;
        Node<E> temp = null;

        temp = header.getNext();
        while (currentPosition < index){
            temp = temp.getNext();
            currentPosition++;
        }
        // temp points to the node at position index
        return temp; // return reference to the Node
    }
    @Override
    public E get(int index) {
        if ((index < 0) || (index >= this.size())){
            throw new IndexOutOfBoundsException("index is out range.");
        }
        Node<E> target = this.getPosition(index);
        return target.getElement();
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public int removeAll(E e) {
        return 0;
    }

    @Override
    public E replace(int index, E newElement) {
        if ((index < 0) || (index >= this.size())){
            throw new IndexOutOfBoundsException("index is out range.");
        }
        Node<E> target = this.getPosition(index);
        E result = target.getElement();
        target.setElement(newElement);
        return result;
    }

    @Override
    public void clear() {
        while(!this.isEmpty()){
            this.remove(0);
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public void print(PrintStream out) {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
