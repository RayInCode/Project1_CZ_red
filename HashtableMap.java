// --== CS400 Project One File Header ==--
// Name: Haotong Xin
// CSL Username: haotong
// Email: hxin8@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashtableMap<KeyType, ValueType>
    implements IterableMapADT<KeyType, ValueType>, IISBNValidator {
  protected LinkedList[] map;

  /**
   * Create a new HashtableMap
   * 
   * @param capacity the capacity of the HashtableMap
   */
  public HashtableMap(int capacity) {
    map = new LinkedList[capacity];
  }

  /**
   * Create a new HashtableMap with default capacity of 15
   */
  public HashtableMap() {
    map = new LinkedList[15];
  }// with default capacity = 15

  /**
   * Double the size of the HashtableMap and rehash all the key value pairs inside
   */
  private void rehash() {
    LinkedList[] oldMap = map;
    map = new LinkedList[map.length * 2];
    for (LinkedList list : oldMap) {
      if (list != null) {
        for (Object o : list) {
          Node node = (Node) o;
          this.put(node.getKey(), node.getValue());
        }
      }
    }
  }

  /**
   * Inserts a new (key, value) pair into the map if the map does not contain a value mapped to key
   * yet.
   * 
   * @param key   the key of the (key, value) pair to store
   * @param value the value that the key will map to
   * @return true if the (key, value) pair was inserted into the map, false if a mapping for key
   *         already exists and the new (key, value) pair could not be inserted
   */
  public boolean put(Object key, Object value) {
    if (key == null) {
      return false;
    }
    if (map[Math.abs(key.hashCode()) % map.length] == null) {
      LinkedList pairs = new LinkedList<Node>();
      pairs.add(new Node(key, value));
      map[Math.abs(key.hashCode()) % map.length] = pairs;
      if ((float) this.size() / (float) map.length >= 0.7) {
        rehash();
      }
      return true;
    } else {
      for (Object o : map[Math.abs(key.hashCode()) % map.length]) {
        if (((Node) o).getKey().equals(key)) {
          return false;
        }
      }
      map[Math.abs(key.hashCode()) % map.length].add(new Node(key, value));
      if ((float) this.size() / (float) map.length >= 0.7) {
        rehash();
      }
    }
    return true;
  }

  /**
   * Returns the value mapped to a key if the map contains such a mapping.
   * 
   * @param key the key for which to look up the value
   * @return the value mapped to the key
   * @throws NoSuchElementException if the map does not contain a mapping for the key
   */
  public Object get(Object key) throws NoSuchElementException {
    if (map[Math.abs(key.hashCode()) % map.length] == null) {
      throw new NoSuchElementException("no such key in the hashmap");
    }
    for (Object o : map[Math.abs(key.hashCode()) % map.length]) {
      if (((Node) o).getKey().equals(key)) {
        return ((Node) o).getValue();
      }
    }
    throw new NoSuchElementException("no such key in the hashmap");
  }

  /**
   * Removes a key and its value from the map.
   * 
   * @param key the key for the (key, value) pair to remove
   * @return the value for the (key, value) pair that was removed, or null if the map did not
   *         contain a mapping for key
   */
  public Object remove(Object key) {
    if (map[Math.abs(key.hashCode()) % map.length] == null) {
      return null;
    }

    for (Object o : map[Math.abs(key.hashCode()) % map.length]) {
      if (((Node) o).getKey().equals(key)) {
        Object val = ((Node) o).getValue();
        map[Math.abs(key.hashCode()) % map.length].remove(o);
        return val;
      }

    }
    return null;
  }

  /**
   * Checks if a key is stored in the map.
   * 
   * @param key the key to check for
   * @return true if the key is stored (mapped to a value) by the map and false otherwise
   */
  public boolean containsKey(Object key) {
    if (map[Math.abs(key.hashCode()) % map.length] == null) {
      return false;
    }
    for (Object o : map[Math.abs(key.hashCode()) % map.length]) {

      if (((Node) o).getKey().equals(key)) {
        return true;
      }

    }
    return false;
  }

  /**
   * Returns the number of (key, value) pairs stored in the map.
   * 
   * @return the number of (key, value) pairs stored in the map
   */
  public int size() {
    int res = 0;
    for (int i = 0; i < map.length; i++) {
      if (map[i] != null) {
        res += map[i].size();
      }
    }
    return res;
  }

  /**
   * Removes all (key, value) pairs from the map.
   */
  public void clear() {
    for (int i = 0; i < map.length; i++) {
      map[i] = null;
    }
  }

  /**
   * build an iterator for the HashMap
   */
  public Iterator iterator() {
    ArrayList<Object> values = new ArrayList();
    for (LinkedList list : map) {
      if (list != null) {
        for (Object node : list) {
          values.add(((Node) node).getValue());
        }
      }
    }
    Iterator iterator = values.iterator();
    return iterator;
  }
   /**
   * tests whether it the code is a valid ISBN13 code
   * @return true if it is valid, false otherwise
   */
  @Override
  public boolean validate(String isbn13) {
    int x1=Integer.valueOf(isbn13.substring(0,1));
    int x2=Integer.valueOf(isbn13.substring(1,2));
    int x3=Integer.valueOf(isbn13.substring(2,3));
    int x4=Integer.valueOf(isbn13.substring(4,5));
    int x5=Integer.valueOf(isbn13.substring(6,7));
    int x6=Integer.valueOf(isbn13.substring(7,8));
    int x7=Integer.valueOf(isbn13.substring(8,9));
    int x8=Integer.valueOf(isbn13.substring(10,11));
    int x9=Integer.valueOf(isbn13.substring(11,12));
    int x10=Integer.valueOf(isbn13.substring(12,13));
    int x11=Integer.valueOf(isbn13.substring(13,14));
    int x12=Integer.valueOf(isbn13.substring(14,15));
    int x13=Integer.valueOf(isbn13.substring(16));
    int sum=x1+3*x2+x3+3*x4+x5+3*x6+x7+3*x8+x9+3*x10+x11+3*x12+x13;
    if(sum%10==0){
            return true;
    }
    return false;
  }
}
