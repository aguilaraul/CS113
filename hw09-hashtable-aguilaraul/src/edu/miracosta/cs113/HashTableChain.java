package edu.miracosta.cs113;

import java.util.*;

public class HashTableChain<K, V> implements Map<K, V> {

    /** The table */
    private LinkedList<Entry<K, V>>[] table;
    /** The number of keys */
    private int numKeys;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 3.0;

    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0) {
            index += table.length;
        }
        if(table[index] == null) {
            return null;
        }

        // Search the list at tabel[index] to find the key
        for(Entry<K, V> item:table[index]) {
            if(item.getKey().equals(key)) {
                return item.getValue();
            }
        }

        // Return null if key is not in table
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = key.hashCode() % table.length;
        if(index < 0) {
            index += table.length;
        }

        // Search table for key at index
        for (Entry<K, V> item : table[index]) {
            if(item.getKey().equals(key)) {
                return true;
            }
        }

        // not in table
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Entry<K, V>> bucket : table) {
            if(bucket != null) {
                for (Entry<K, V> item : bucket) {
                    if (item.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if(index < 0) {
            index += table.length;
        }
        // Create a new linked list if table[index] is null
        if(table[index] == null) {
            table[index] = new LinkedList<Entry<K, V>>();
        }

        // Search the list at table[index] to find the key
        for(Entry<K,V> item: table[index]) {
            if(item.getKey().equals(key)) {
                // Replace value for this key
                V oldVal = item.getValue();
                item.setValue(value);
                return oldVal;
            }
        }

        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++;
        if(numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;

        if(index < 0) {
            index += table.length;
        }

        // Key is not in table return null
        if(table[index] == null) {
            return null;
        }

        // Search the list table[index] to find the key
        for(Entry<K, V> item : table[index]) {
            // if search is successful, remove entry, decrement numKeys and return value
            if(item.getKey().equals(key)) {
                V oldValue = item.getValue();
                table[index].remove(item);
                numKeys--;
                // if the list at table[index] is empty set table[index] to null
                if(table[index].isEmpty()) {
                    table[index] = null;
                }
                return oldValue;
            }
        }

        // The key is not in the table return null
        return null;
    }

    @Override
    public void clear() {
        table = new LinkedList[table.length];
        numKeys = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>(size());
        for(LinkedList<Entry<K, V>> bucket : table) {
            if(bucket != null) {
                for(Entry<K, V> item : bucket) {
                    if(item != null) {
                        keySet.add(item.getKey());
                    }
                }
            }
        }
        return keySet;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> item : bucket) {
                    result += ( ((item.getKey() == null) ? 0 : item.getKey().hashCode()) ^
                                ((item.getValue() == null) ? 0 : item.getValue().hashCode()) );
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }

        Map<K, V> other = (Map<K, V>) obj;

        return this.hashCode() == other.hashCode();
    }

    /**
     * Rebuild and refill the table once it has passed the load threshold
     */
    private void rehash() {
        // Save old table
        LinkedList<Entry<K, V>>[] oldTable = table;
        // Double size of this table
        table = new LinkedList[2 * oldTable.length + 1];

        // Reinsert all items in oldTable to expanded table
        numKeys = 0;
        for (LinkedList<Entry<K, V>> index : oldTable) {
            if(index != null) {
                for (Entry<K, V> item : index) {
                    put(item.key, item.value);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < table.length; i++) {
            sb.append("[" + i + "]");
            if(table[i] != null) {
                for(Entry<K,V> item: table[i]) {
                    sb.append("-> " + item + " ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /* DO NOT IMPLEMENT */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    /**
     * Contains key-value pairs for a hash table
     */
    public class Entry<K, V> {
        private K key;
        private V value;

        /**
         * Creates a new key-value pair
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value
         * @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value
         * @param val The new value
         * @return The previous value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Inner class to implement the set view
     * @param <K> the key
     * @param <V> the value
     */
    public class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public int size() {
            return numKeys;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }
    }

    public class SetIterator implements Iterator {
        /** Keep track of the next value of the iterator */
        int index = 0;
        /**
         * Keeps track of the index of the last item returned by next
         * This is used by the remove method
         */
        Entry<K, V> lastItemReturned  = null;
        Iterator<Entry<K, V>> iter = null;

        @Override
        public boolean hasNext() {
            if(iter != null && iter.hasNext()) {
                return true;
            }
            do {
                index++;
                if(index >= table.length) {
                    return false;
                }
            } while(table[index] == null);
            iter = table[index].iterator();
            return iter.hasNext();
        }

        @Override
        public Object next() {
            if(iter.hasNext()) {
                lastItemReturned = iter.next();
                return lastItemReturned;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(lastItemReturned == null) {
                throw new IllegalStateException();
            } else {
                iter.remove();
                lastItemReturned = null;
            }
        }
    }
}
