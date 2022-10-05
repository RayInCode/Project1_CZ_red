// --== CS400 Project One File Header ==--
// Name: Shuaijie Li
// CSL Username: shuaijie
// Email: sli964@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: None

import java.util.Iterator;

/**
 * A placeholder class extends the HashtableMap class with an iterator
 *
 * @param <KeyType> the type of keys
 * @param <ValueType> the type of values
 *
 * @author Shuaijie Li (BD)
 */
public class HashtableMapBD<KeyType, ValueType> extends HashtableMap<KeyType, ValueType>
        implements IterableMapADT<KeyType, ValueType>{
    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ValueType> iterator() {
        return new BookIteratorBD<>(this);
    }
}

