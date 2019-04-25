package edu.miracosta.cs113;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> implements StackInterface<E> {

    private ArrayList<E> stack;

    public ArrayListStack() {
        stack = new ArrayList<E>();
    }

    /**
     * Returns true if the stack is empty otherwise returns false
     * @return true if stack is empty otherwise returns false
     */
    @Override
    public boolean empty() {
        return stack.isEmpty();
    }

    /**
     * Returns object at the top of stack without removing it
     * @return reference (shallow copy) of object at top of stack
     */
    @Override
    public E peek() {
        if(empty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size()-1);
    }

    /**
     * Returns the object at the top of the stack and removes it
     * @return reference of removed object from top of stack
     */
    @Override
    public E pop() {
        if(empty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size()-1);
    }

    /**
     * Pushes an item onto the top of the stack and returns the item pushed
     * @param obj object to push onto top of stack
     * @return the item pushed onto the stack
     */
    @Override
    public E push(E obj) {
        stack.add(obj);
        return obj;
    }
}
