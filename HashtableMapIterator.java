import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashtableMapIterator<ValueType> implements java.util.Iterator<ValueType> {
  private int number = 0;
  private LinkedList next;
  private int mapSize;
  private int mapLength;
  private int listNumber = 0;
  private int listIndex;
  private LinkedList[] hashMap;

  /**
   * builds a hashtable Iterator
   * 
   * @param map the whole HashtableMap
   */
  public HashtableMapIterator(LinkedList[] map) {
    hashMap = map;
    int mapSize = 0;
    int mapLength = map.length;
    for (int i = 0; i < map.length; i++) {
      if (map[i] != null) {
        mapSize += map[i].size();
      }
    }
    for (int i = 0; i < map.length; i++) {
      if (map[i] != null) {
        next = map[i];
        listIndex = i;
        break;
      }
    }
  }

  /**
   * returns whether there is a following element in the iterator
   */
  @Override
  public boolean hasNext() {
    if (number + 1 >= mapSize) {
      return false;
    }
    return true;

  }

  /**
   * returns the next value in the hashmap
   */
  @Override
  public ValueType next() {
    if (!hasNext()) {
      throw new NoSuchElementException("there is no next value");
    }
    if (listNumber + 1 >= next.size()) {
      LinkedList tempList = next;
      for (int i = listIndex + 1; i < mapLength; i++) {
        if (hashMap[i] != null) {
          next = hashMap[i];
          listIndex = i;
        }
      }
      number++;
      listNumber = 0;
      return (ValueType) ((Node) tempList.getLast()).getValue();

    }
    number++;
    return (ValueType) ((Node) next.get(listNumber++)).getValue();
  }

}
