package edu.miracosta.cs113;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CircularArrayQueue<E> implements Queue<E> {

    private final int DEFAULT_CAPACITY = 10;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    private E[] queue;

    /**
     * Constructs an empty queue using the default capacity
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue() {
        capacity = DEFAULT_CAPACITY;
        queue = (E[])new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }

    /**
     * Constructs an empty queue with the specified initial capacity
     * @param initCapacity the initial capacity
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int initCapacity) {
        capacity = initCapacity;
        queue = (E[])new Object[capacity];
        front = 0;
        rear = capacity - 1;
        size = 0;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning true upon
     * success and throwing an IllegalStateException if no space is currently
     * available
     * @param e element to be inserted to rear
     * @return true if successful, throws IllegalStateException if not
     */
    @Override
    public boolean add(E e) {
        if(size == capacity) {
            reallocate();
            //throw new IllegalStateException();
        }
        size++;
        rear = (rear + 1) % capacity;
        queue[rear] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions
     * @param e the element to be inserted to rear
     * @return true if successful, else false if the item could not be inserted
     */
    public boolean offer(E e) {
        if(size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;
        queue[rear] = e;
        return true;
    }

    /**
     * Retrieves and removes the head of this queue. Throws NoSuchElementException
     * if this queue is empty
     * @return the head of this queue
     */
    public E remove() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        E result = queue[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue
     * is empty
     * @return the head of the queue or null if this queue is empty
     */
    public E poll() {
        if(isEmpty()) {
            return null;
        }
        E result = queue[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /**
     * Retrieves but does not the remove the head of this queue. Throws NoSuchElementException
     * if this queue is empty
     * @return the head of this queue
     */
    public E element() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        return queue[front];
    }

    /**
     * Retrieves but does not remove the head of this queue or returns null if this
     * queue is empty
     * @return the head of this queue or null if this queue is empty
     */
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return queue[front];
    }

    /**
     * Returns the number of elements in this collection. If this collection con
     * @return the number of elements in this collection
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no elements
     * @return true if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Doubles the array size and allocates the old queue
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = 2 * capacity;
        E[] newQueue = (E[])new Object[newCapacity];
        int j = front;
        for(int i = 0; i < size; i++) {
            newQueue[i] = queue[j];
            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        queue = newQueue;
    }
}
