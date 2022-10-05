// --== CS400 Project One File Header ==--
// Name: Shuaijie Li
// CSL Username: shuaijie
// Email: sli964@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: None

/**
 * A class storing pairs of keys and values
 *
 * @param <KeyType> the type of keys
 * @param <ValueType> the type of values
 */
public class KVPair<KeyType, ValueType> {
    protected KeyType key;
    protected ValueType value;
    public KVPair(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }
}
