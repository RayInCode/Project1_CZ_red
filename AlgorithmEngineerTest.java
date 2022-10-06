import java.util.NoSuchElementException;
import java.util.Iterator;

public class AlgorithmEngineerTest {

  public static void main(String[] arg) {
    System.out.println("run test1:" + test1());
    System.out.println("run test2:" + test2());
    System.out.println("run test3:" + test3());
    System.out.println("run test4:" + test4());
    System.out.println("run test5:" + test5());
  }

  /**
   * tests the correctness of constructor, put() method and get() method
   * 
   * @return true if everything works well, false otherwise
   */
  public static boolean test1() {
    try {
      HashtableMap map1 = new HashtableMap(4);
      map1.put(1, 10);
      map1.put(2, 18);
      if (map1.map.length != 4) {
        System.out.println("the method put() is growing the size of array when it should not");
        return false;
      }
      if ((int) map1.get(1) != 10) {
        System.out.println("the method get() is not returning the correct value");
        return false;
      }
      map1.put(3, 87);
      if (map1.map.length != 8) {
        System.out.println("the method put() is not growing the size of array when it should");
        return false;
      }
      map1.get(10);
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      System.out.println("unexpected exception is thrown");
      return false;
    }
    return true;
  }

  /**
   * check the correctness of remove() method and containsKey() method
   * 
   * @return true if everything works well, false otherwise
   */
  public static boolean test2() {
    try {
      HashtableMap map1 = new HashtableMap(4);
      map1.put(1, 10);
      map1.put(2, 18);
      if ((int) map1.remove(2) != 18) {
        System.out.println("the method remove() is not returning the correct value");
        return false;
      }
      if (map1.size() != 1) {
        System.out.println("the method remove() is not removing the correct key and value");
        return false;
      }
      if (map1.remove(8) != null) {
        System.out.println("the method remove() is not returning null when it should");
        return false;
      }
      if (map1.containsKey(1) != true) {
        System.out.println("the method containsKey() is not returning true when it should");
        return false;
      }
      if (map1.containsKey(10) == true) {
        System.out.println("the method containsKey() is not returning false when it should");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      System.out.println("unexpected exception is thrown");
      return false;
    }
    return true;
  }

  /**
   * check the correctness of iterator() method
   * 
   * @return true if everything works well, false otherwise
   */
  public static boolean test3() {
    try {
      HashtableMap map1 = new HashtableMap(8);
      map1.put(1, 10);
      map1.put(2, 18);
      map1.put(9, 8);
      Iterator ite = map1.iterator();
      ite.next();
      if ((int) ite.next() != 8) {
        System.out.println("iterator isn't returning the correct value");
        return false;
      }
      if (ite.hasNext() == false) {
        System.out.println("iterator isn't returning the correct hasNext() value");
        return false;
      }
      if ((int) ite.next() != 18) {
        System.out.println("iterator isn't returning the correct value");
        return false;
      }
      ite.next();
      System.out.println("iterator isn't throwing an exception when there is no more element");
      return false;
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      System.out.println("unexpected exception is thrown");
      return false;
    }
    return true;
  }

  /**
   * check the correctness of ISBNValidator method
   * 
   * @return true if everything works well, false otherwise
   */
  public static boolean test4() {
    try {
      HashtableMap map1 = new HashtableMap(4);
      if (!map1.validate("978-0-306-40615-7")) {
        System.out.println("validator isn't returning true when it should");
        return false;
      }
      if (map1.validate("978-0-306-40615-9")) {
        System.out.println("validator isn't returning false when it should");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      System.out.println("unexpected exception is thrown");
      return false;
    }
    return true;
  }

  /**
   * check the correctness of size() method and clear() method
   * 
   * @return true if everything works well, false otherwise
   */
  public static boolean test5() {
    try {
      HashtableMap map1 = new HashtableMap(4);
      map1.put(1, 10);
      map1.put(2, 18);
      if (map1.size() != 2) {
        System.out.println("the method size() is not returning the correct value");
        return false;
      }
      map1.put(10, 10000);
      if (map1.size() != 3) {
        System.out.println("the method size() is not returning the correct value");
        return false;
      }
      map1.clear();
      if (map1.size() != 0) {
        System.out.println("the method clear() is not emptying the hashtable");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      System.out.println("unexpected exception is thrown");
      return false;
    }
    return true;
  }
}

