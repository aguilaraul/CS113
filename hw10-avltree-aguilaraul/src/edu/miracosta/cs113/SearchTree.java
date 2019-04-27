package edu.miracosta.cs113;

public interface SearchTree<E> {

    /**
     * Inserts item where it belongs in the tree
     * @param item to be inserted
     * @return true if item is inserted, false if it isn't (already in tree)
     */
    boolean add(E item);

    /**
     * Returns true if target is found in tree
     * @param target to be found in tree
     * @return tree if target is found, otherwise false
     */
    boolean contains(E target);

    /**
     * Returns a reference to the data in the node that is equal to target
     * If no such node is found, return null
     * @param target data to be found
     * @return reference to the data in node that is equal to target
     *          null if no node is found
     */
    E find(E target);

    /**
     * Returns target (if found) from tree and returns it; otherwise returns
     * null
     * @param target data to delete
     * @return data that is removed, otherwise null
     */
    E delete(E target);

    /**
     * Removes target (if found) from tree and returns true; otherwise, returns
     * false
     * @param target data to delete
     * @return true if target is found and deleted, otherwise false
     */
    boolean remove(E target);
}
