// --== CS400 Project One File Header ==--
// Name: Shuaijie Li
// CSL Username: shuaijie
// Email: sli964@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: None

import java.util.List;

/**
 * A class tests the functions defined in the BookMapperBackend class
 *
 * @author Shuaijie Li (BD)
 */
public class BackendDeveloperTest {

    /**
     * Check the correctness of addBook defined in the BookMapperBackend class
     *
     * @return true if the test verifies a correct functionally and false if any bug detected
     */
    public static boolean test1() {
        // Define an array of books to be stored
        BookBD[] bookList = new BookBD[3];
        bookList[0] = new BookBD("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling", "9780439785969");
        bookList[1] = new BookBD("A Short History of Nearly Everything",
                "Bill Bryson", "9780767908184");
        bookList[2] = new BookBD("In a Sunburned Country",
                "Bill Bryson", "9780767903868");
        BookMapperBackend testDatabase = new BookMapperBackend();

        // Implement addBook
        for (BookBD book: bookList)
            testDatabase.addBook(book);

        // Check if books are added to the database
        if (!testDatabase.database.get("9780439785969").getAuthors().equals("J.K. Rowling") ||
                !testDatabase.database.get("9780767908184").getAuthors().equals("Bill Bryson") ||
                !testDatabase.database.get("9780767903868").getAuthors().equals("Bill Bryson"))
        {
            System.out.println("test1. Problem detected: Your addBook did not add books into " +
                    "the database as expected.");
            return false;
        }

        return true; // no bug detected
    }

    /**
     * Check the correctness of getNumberOfBooks defined in the BookMapperBackend class
     *
     * @return true if the test verifies a correct functionally and false if any bug detected
     */
    public static boolean test2() {
        // Define an array
        BookBD[] bookList = new BookBD[3];
        bookList[0] = new BookBD("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling", "9780439785969");
        bookList[1] = new BookBD("A Short History of Nearly Everything",
                "Bill Bryson", "9780767908184");
        bookList[2] = new BookBD("In a Sunburned Country",
                "Bill Bryson", "9780767903868");
        BookMapperBackend testDatabase = new BookMapperBackend();

        for (BookBD book: bookList) {
            testDatabase.addBook(book);
        }

        // Check the correctness of output
        if (testDatabase.getNumberOfBooks() != 3) {
            System.out.println("test2. Problem detected: " +
                    "Your getNumberOfBooks did not return the correct output.");
            return false;
        }
        return true;
    }

    /**
     * Check the correctness of setAuthorFilter and getAuthorFilter defined in the BookMapperBackend class
     *
     * @return true if the test verifies a correct functionally and false if any bug detected
     */
    public static boolean test3() {
        // Define an array of books
        BookBD[] bookList = new BookBD[3];
        bookList[0] = new BookBD("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling", "9780439785969");
        bookList[1] = new BookBD("A Short History of Nearly Everything",
                "Bill Bryson", "9780767908184");
        bookList[2] = new BookBD("In a Sunburned Country",
                "Bill Bryson", "9780767903868");
        BookMapperBackend testDatabase = new BookMapperBackend();

        for (BookBD book: bookList) {
            testDatabase.addBook(book);
        }

        // Implement setAuthorFilter
        testDatabase.setAuthorFilter("bill");

        // Check the authorFilter List
        if (!testDatabase.authorFilter.contains(bookList[1]) ||
                !testDatabase.authorFilter.contains(bookList[2])) {
            System.out.println("test3. Problem detected: Your setAuthorFilter " +
                    "did not set an authorFilter list as expected.");
            return false;
        }

        // Check the output of getAuthorFilter
        if (!testDatabase.getAuthorFilter().equals("bill")) {
            System.out.println("test3. Problem detected: Your getAuthorFilter " +
                    "did not return the correct output.");
            return false;
        }

        return true; // no bug detected
    }

    /**
     * Check the correctness of resetAuthorFilter defined in the BookMapperBackend class
     *
     * @return true if the test verifies a correct functionally and false if any bug detected
     */
    public static boolean test4() {
        // Define an array of books
        BookBD[] bookList = new BookBD[3];
        bookList[0] = new BookBD("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling", "9780439785969");
        bookList[1] = new BookBD("A Short History of Nearly Everything",
                "Bill Bryson", "9780767908184");
        bookList[2] = new BookBD("In a Sunburned Country",
                "Bill Bryson", "9780767903868");
        BookMapperBackend testDatabase = new BookMapperBackend();

        for (BookBD book: bookList) {
            testDatabase.addBook(book);
        }

        // Implement setAuthorFilter
        testDatabase.setAuthorFilter("bill");

        // Implement resetAuthorFiler
        testDatabase.resetAuthorFilter();

        // Check the authorFilter List
        if (testDatabase.authorFilter.size() != 0) {
            System.out.println("test4. Problem detected: Your resetAuthorFilter " +
                    "did not reset the authorFilter list.");
            return false;
        }

        // Check the string used as filter
        if (testDatabase.getAuthorFilter() != null) {
            System.out.println("test4. Problem detected: Your getAuthorFilter " +
                    "did not reset the string used as filter.");
            return false;
        }

        return true; // no bug detected
    }

    /**
     * Check the correctness of searchByTitleWord defined in the BookMapperBackend class
     *
     * @return true if the test verifies a correct functionally and false if any bug detected
     */
    public static boolean test5() {
        // Define an array of books
        BookBD[] bookList = new BookBD[4];
        bookList[0] = new BookBD("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling", "9780439785969");
        bookList[1] = new BookBD("Harry Potter and the Order of the Phoenix (Harry Potter  #5)",
                "J.K. Rowling", "9780439358071");
        bookList[2] = new BookBD("A Short History of Nearly Everything",
                "Bill Bryson", "9780767908184");
        bookList[3] = new BookBD("In a Sunburned Country",
                "Bill Bryson", "9780767903868");
        BookMapperBackend testDatabase = new BookMapperBackend();

        // Store books into database
        for (BookBD book: bookList)
            testDatabase.addBook(book);

        // 1. Search without author filter
        {
            List<IBook> searchResult = testDatabase.searchByTitleWord("harry");

            // Check the correctness of output
            if (!searchResult.contains(bookList[0]) || !searchResult.contains(bookList[1])) {
                System.out.println("test5-scenario 1. Problem detected: Your searchByTitleWord " +
                        "did not return the expected output when searching without author filter.");
                return false;
            }
        }

        // 2. Search with author filter set
        {
            // Ser author filter
            testDatabase.setAuthorFilter("bill");

            List<IBook> searchResult1 = testDatabase.searchByTitleWord("harry");
            List<IBook> searchResult2 = testDatabase.searchByTitleWord("history");

            // Check the correctness of output
            if (searchResult1.size() != 0 || !searchResult2.contains(bookList[2])) {
                System.out.println("test5-scenario 2. Problem detected: Your searchByTitleWord " +
                        "did not return the expected output when author filter was set.");
                return false;
            }
        }

        return true; // no bug detected
    }

    /**
     * Check the correctness of getByISBN defined in the BookMapperBackend class
     *
     * @return true if the test verifies a correct functionally and false if any bug detected
     */
    public static boolean test6() {
        // Define a database and add a book
        BookMapperBackend testDatabase = new BookMapperBackend();
        BookBD book = new BookBD("Harry Potter and the Half-Blood Prince (Harry Potter  #6)",
                "J.K. Rowling", "9780439785969");
        testDatabase.addBook(book);

        // Check the correctness of output
        if (!testDatabase.getByISBN("9780439785969").equals(book)) {
            System.out.println("test6. Problem detected: Your getByISBN " +
                    "did not return the expected output.");
            return false;
        }

        return true; // no bug detected
    }

    public static void main(String[] args) {
        System.out.println("addBook(): " + test1());
        System.out.println("getNumberOfBooks(): " + test2());
        System.out.println("setAuthorFilter() & getAuthorFilter(): " + test3());
        System.out.println("resetAuthorFilter(): " + test4());
        System.out.println("searchByTitleWord(): " + test5());
        System.out.println("getByISBN(): " + test6());
    }
}
