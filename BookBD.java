// --== CS400 Project One File Header ==--
// Name: Shuaijie Li
// CSL Username: shuaijie
// Email: sli964@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: None

/**
 * A placeholder class storing some information of a book
 *
 * @author Shuaijie Li (BD)
 */
public class BookBD implements IBook{
    private String title;
    private String author;
    private String isbn;

    public BookBD(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Returns the title of the book.
     *
     * @return title of the book
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns a string that contains the authors of the book
     * as a single string with different authors separated by /.
     *
     * @return author names as single string
     */
    @Override
    public String getAuthors() {
        return this.author;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     *
     * @return ISBN number of book
     */
    @Override
    public String getISBN13() {
        return this.isbn;
    }
}
