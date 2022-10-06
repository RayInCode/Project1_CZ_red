// --== CS400 Project One File Header ==--
// Name: Haotong Xin
// CSL Username: haotong
// Email: hxin8@wisc.edu
// Lecture #: 002
// Notes to Grader: <any optional extra notes to your grader>
public class Node<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;

  /**
   * Create a Node
   * 
   * @param key   the key of the Node
   * @param value the value of the Node
   */
  public Node(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * return the key of the Node
   * 
   * @return the key of the Node
   */
  public KeyType getKey() {
    return key;
  }

  /**
   * return the value of the Node
   * 
   * @return the value of the Node
   */
  public ValueType getValue() {
    return value;
  }
}

