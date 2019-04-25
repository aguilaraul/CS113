package edu.miracosta.cs113;

import java.util.*;
import java.util.function.UnaryOperator;

public class DoubleLinkedList<E> implements List<E> {


    public static void main(String[] args) {
        DoubleLinkedList<Integer> testList = new DoubleLinkedList<Integer>();

        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
        testList.add(6);

        System.out.println(testList.toString());
        System.out.println("Contains: " + testList.contains(1));
        System.out.println("Get (0): " + testList.get(0));
        System.out.println("indexOf (1): " + testList.indexOf(1));
        System.out.println(testList.size());
        System.out.println(testList.isEmpty());
        testList.clear();
        System.out.println(testList.toString());
        System.out.println(testList.size());
        System.out.println(testList.isEmpty());
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public String toString() {
        Node<E> nodeRef = head;
        StringBuilder result = new StringBuilder();
        result.append("[");
        while(nodeRef != null) {
            result.append(nodeRef.data);
            if(nodeRef.next != null) {
                result.append(", ");
            }
            nodeRef = nodeRef.next;
        }
        result.append("]");
        return result.toString();
    }

    public void addFirst(E item) {
        head = new Node<E>(item, head);
        size++;
    }

    private void addAfter(Node<E> node, E item) {
        node.next = new Node<E>(item, node.next);
        size++;
    }

    private E removeFirst() {
        Node<E> temp = head;
        if(head != null) {
            head = head.next;
        }
        if(temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if(temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    private Node<E> getNode(int index) {
        Node<E> node = head;
        for(int i = 0; i < index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    @Override
    public E set(int index, E element) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        Node<E> node = getNode(index);
        E result = node.data;
        node.data = element;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0 && head == null);
    }

    @Override
    public boolean contains(Object o) {
        Node current = head;
        if(current.data.equals(o)) {
            return true;
        }
        while(current.next != null) {
            if(current.next.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
           throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        if(index == 0) {
            addFirst(element);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, element);
        }
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node current = head;

        while(current.next != null) {
            if(!o.equals(get(index))) {
                index++;
            } else {
                return index;
            }
            current = current.next;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DoubleListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new DoubleListIterator(index);
    }

    @Override
    public boolean remove(Object o) {
        Node current = head;
        while (current.next != null) {
            if(current.data.equals(o)) {
                current = current
                        .next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /* Do Not Implement */

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public int hashCode() {
        return -1;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public <E> E[] toArray(E[] e) {
        return null;
    }

    @Override
    public void sort(Comparator<? super E> c) {
    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }

    private class DoubleListIterator implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;

        public DoubleListIterator(int i) {
            if(i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null;
            if(i == size) {
                index = size;
                nextItem = null;
            } else {
                nextItem = head;
                for(index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<E>(e);

            if(isEmpty()) {
                head = newNode;
                tail = head;
            } else if (nextItem == head) {
                newNode.next = nextItem;
                nextItem.prev = newNode;
                head = newNode;
            } else if (nextItem == null) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                // Link newNode to nextItem.prev
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                // Link newNode to the nextItem
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
        }

        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;
            return lastItemReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return (nextItem == null && size != 0) || nextItem.prev != null;
        }

        @Override
        public E previous() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextItem == null) {
                nextItem = tail;
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;
            return lastItemReturned.data;
        }

        @Override
        public int nextIndex() {
            if(hasNext()) {
                return index + 1;
            }
            return size;
        }

        @Override
        public int previousIndex() {
            if(hasPrevious()) {
                return index - 1;
            }
            return -1;
        }

        @Override
        public void remove() {
            nextItem.prev = lastItemReturned.prev;
            lastItemReturned.prev.next = nextItem;
        }

        @Override
        public void set(E e) {
            lastItemReturned.data = e;
        }
    }

    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dateItem) {
            data = dateItem;
        }

        private Node(E dateItem, Node<E> nodeRef) {
            data = dateItem;
            next = nodeRef;
        }
    }
}
