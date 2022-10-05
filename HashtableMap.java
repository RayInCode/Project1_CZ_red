// --== CS400 Project One File Header ==--
// Name: Shuaijie Li
// CSL Username: shuaijie
// Email: sli964@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: None

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A class implements an individual hashtable.
 *
 * @param <KeyType> the type of keys
 * @param <ValueType> the type of values
 *
 */
public class HashtableMap <KeyType, ValueType> implements MapADT <KeyType, ValueType> {
    private int capacity; // capacity of the hashtable
    private int size; // number of (key, value) pairs stored
    protected LinkedList[] table; // array of chains that store pairs

    /**
     * Constructs a new hashtable with an input capacity.
     *
     * @param capacity the capacity of the hashtable
     */
    public HashtableMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        table = new LinkedList[this.capacity];
    }

    /**
     * Constructs a new hashtable with a default capacity.
     */
    public HashtableMap() {
        // with default capacity = 15
        this.capacity = 15;
        this.size = 0;
        table = new LinkedList[this.capacity];
    }

    /**
     * Gets the index of the bucket where the input key should be put
     *
     * @param key the key for which to look up the value
     * @return the value of the index
     */
    private int getIndex(KeyType key){
        int hash = key.hashCode();
        return Math.abs(hash) % this.capacity;
    }

    @Override
    public boolean put(KeyType key, ValueType value) {
        // puts when the key is not contained
        // and both the key and the value is not null
        if (!this.containsKey(key) && key != null && value != null) {
            // locates the chain
            int index = this.getIndex(key);
            @SuppressWarnings("unchecked")
            LinkedList<KVPair<KeyType, ValueType>> chain =
                    (LinkedList<KVPair<KeyType, ValueType>>) table[index];

            // Checks whether the chain at the index of the table is initialized or not
            // if not, initializes the chain
            if (chain == null) {
                chain = new LinkedList<>();
                table[index] = chain;
            }

            // adds the pair at the end of the chain
            KVPair<KeyType, ValueType> pair = new KVPair<>(key, value);
            chain.add(pair);
            this.size++;

            // Grow the hashtable by doubling its capacity and rehashing
            int loadFactor = this.size * 100 / this.capacity;
            if (loadFactor >= 70) {
                this.rehash();
            }

            return true;
        }

        return false;
    }

    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        // locates the chain
        int index = this.getIndex(key);
        @SuppressWarnings("unchecked")
        LinkedList<KVPair<KeyType, ValueType>> chain =
                (LinkedList<KVPair<KeyType, ValueType>>) table[index];

        // if the chain is null, the key is not contained
        if (chain != null) {
            // searches the pair in the chain
            for (KVPair<KeyType, ValueType> pair : chain) {
                if (key.equals(pair.key))
                    return pair.value;
            }
        }

        // the key is not contained, throw an exception
        throw new NoSuchElementException("No such key!");
    }

    @Override
    public ValueType remove(KeyType key) {
        // locates the chain
        int index = this.getIndex(key);
        @SuppressWarnings("unchecked")
        LinkedList<KVPair<KeyType, ValueType>> chain =
                (LinkedList<KVPair<KeyType, ValueType>>) table[index];

        // if the chain is null, the key is not contained
        if (chain != null) {
            for (KVPair<KeyType, ValueType> pair : chain) {
                // searches the key in the chain
                if (key.equals(pair.key)) {
                    ValueType temp = pair.value;
                    chain.remove(pair);
                    size--;
                    return temp;
                }
            }
        }

        // the key is not contained
        return null;
    }

    @Override
    public boolean containsKey(KeyType key) {
        // locates the chain
        int index = this.getIndex(key);
        @SuppressWarnings("unchecked")
        LinkedList<KVPair<KeyType, ValueType>> chain =
                (LinkedList<KVPair<KeyType, ValueType>>) table[index];

        // if the chain is null, the key is not contained
        if (chain != null) {
            // searches the key in the chain
            for (KVPair<KeyType, ValueType> pair : chain) {
                if (key.equals(pair.key)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (Object chain: table) {
            // if a chain is null, it is empty
            // if not, it will be cleared
            if (chain != null) {
                @SuppressWarnings("unchecked")
                LinkedList<KVPair<KeyType, ValueType>> linkedList =
                        (LinkedList<KVPair<KeyType, ValueType>>) chain;
                linkedList.clear();
                this.size = 0;
            }
        }
    }

    private void rehash() {
        // copies the old hashtable
        @SuppressWarnings("unchecked")
        LinkedList<KVPair<KeyType, ValueType>>[] oldTable =
                (LinkedList<KVPair<KeyType,ValueType>>[]) table;

        // construct a new hashtable with double capacity
        this.capacity *= 2;
        table = new LinkedList[this.capacity];

        // rehash all the pairs in the old hashtable into the new one
        for (int i = this.capacity; i > 0; i--) {
            for (LinkedList<KVPair<KeyType, ValueType>> oldChain : oldTable) {
                if (oldChain != null) {
                    for (KVPair<KeyType, ValueType> pair : oldChain) {
                        // locates the chain in the new hashtable
                        int newIndex = this.getIndex(pair.key);
                        @SuppressWarnings("unchecked")
                        LinkedList<KVPair<KeyType, ValueType>> newChain =
                                (LinkedList<KVPair<KeyType,ValueType>>) table[newIndex];

                        // Checks whether the chain is initialized or not
                        // if not, initializes the chain
                        if (newChain == null)
                            newChain = new LinkedList<>();
                        table[newIndex] = newChain;

                        // add the pair in the old hashtable into the new one
                        newChain.add(pair);
                    }
                }
            }
        }
    }
}