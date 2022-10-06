import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataWranglerTest {
    /**
     * this method test whether BookLoader throws FileNotFoundException in the right way
     *
     * @return true if BookLoader throw exception in the right way and false otherwise
     */
    public static boolean test1() {
        BookLoader testLoader = new BookLoader();
        try{
            testLoader.loadBooks("nonexist.csv");
            return false;
        } catch(FileNotFoundException e) {

        } catch(Exception e){
            return false;
        }

        try{
            testLoader.loadBooks("books.csv");
        } catch(FileNotFoundException e) {
            return false;
        } catch(Exception e){
            return false;
        }


        return true;
    }

    /**
     * this method test whether loadBooks() handle shuffle field  correctly
     *
     * @return true if verify the split functionality inside loadBooks(), and false otherwise
     */
    public static boolean test2() {
        BookLoader testLoader = new BookLoader();
        List<IBook> bookList = new ArrayList<>();
        try{
            bookList = testLoader.loadBooks("test2.csv");
            if(!bookList.get(1).getTitle().equals("Harry Potter and the Order of the Phoenix (Harry Potter  #5)") ||
               !bookList.get(1).getAuthors().equals("J.K. Rowling/Mary GrandPre") ||
               !bookList.get(1).getISBN13().equals("9780439358071")) {
                return false;
            }
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * this method test whether loadBooks() handle the commas inside quotes and escape quotes correctly
     *
     * @return true if verify the split functionality inside loadBooks(), and false otherwise
     */
    public static boolean test3() {
        BookLoader testLoader = new BookLoader();
        List<IBook> bookList = new ArrayList<>();
        try{
            bookList = testLoader.loadBooks("test3.csv");
            if(!bookList.get(0).getAuthors().equals("J.K. Rowling/Mary GrandPre,") ||
               !bookList.get(1).getAuthors().equals(",J.K. Rowling/Mary GrandPre") ||
               !bookList.get(1).getISBN13().equals("978043,9358071")) {
                return false;
            }

            if(!bookList.get(0).getISBN13().equals("\"9780439785969\"") ||
               !bookList.get(2).getTitle().equals("\"Harry,Potter and the Chamber of Secrets (Harry Potter  #2)\"")) {
                return false;
            }
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * this method test whether loadBooks() handle line string omitting some tokens
     *
     * @return true if verify the split functionality inside loadBooks(), and false otherwise
     */
    public static boolean test4() {
        BookLoader testLoader = new BookLoader();
        List<IBook> bookList = new ArrayList<>();
        try{
            bookList = testLoader.loadBooks("test4.csv");
            if(!bookList.get(0).getTitle().equals("") ||
               !bookList.get(0).getAuthors().equals("") ||
               !bookList.get(0).getISBN13().equals("") ||
               !bookList.get(1).getISBN13().equals("9780439358071")) {
                return false;
            }
        }catch(Exception e) {
            return false;
        }

        return true;
    }

    /**
     * this method test whether loadBooks() handle given data correctly
     *
     * @return true if verify the split functionality inside loadBooks(), and false otherwise
     */
    public static boolean test5() {
        BookLoader testLoader = new BookLoader();
        List<IBook> bookList = new ArrayList<>();
        try{
            bookList = testLoader.loadBooks("books.csv");
            if(!bookList.get(10).getTitle().equals("The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)") ||
                !bookList.get(8).getAuthors().equals("Douglas Adams") ||
                !bookList.get(9).getISBN13().equals("9781400052929") ||
                !bookList.get(15).getISBN13().equals("9780767903868")) {
                return false;
            }
        }catch(Exception e) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
    }
}
