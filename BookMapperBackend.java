// --== CS400 Project One File Header ==--
// Name: Shuaijie Li
// CSL Username: shuaijie
// Email: sli964@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: None

import java.util.ArrayList;
import java.util.List;

public class BookMapperBackend implements IBookMapperBackend{

    protected HashtableMapBD<String, IBook> database; // a database storing books
    protected List<IBook> authorFilter; // a list storing books filtered by author
    protected String filterBy; // a string used as an author filter

    public BookMapperBackend() {
        this.database = new HashtableMapBD<>();
        this.authorFilter = new ArrayList<>();
        this.filterBy = null;
    }

    /**
     * Adds a new book to the backend's database and is stored in
     * a hash table internally.
     *
     * @param book the book to add
     */
    @Override
    public void addBook(IBook book) {
        this.database.put(book.getISBN13(), book);
    }

    /**
     * Returns the number of books stored in the backend's database.
     *
     * @return the number of books
     */
    @Override
    public int getNumberOfBooks() {
        return this.database.size();
    }

    /**
     * This method can be used to set a filter for the author names
     * contained in the search results. A book is only returned as
     * a result for a search by title, it is also contains the string
     * filterBy in the names of its authors.
     *
     * @param filterBy the string that the book's author names must contain
     */
    @Override
    public void setAuthorFilter(String filterBy) {
        // Set input as filter string
        this.filterBy = filterBy;

        // Search using iterator
        for (IBook book: this.database) {
            String author = book.getAuthors();

            // Match in a non-sensitive way
            if (author.toLowerCase().contains(filterBy.toLowerCase())) {
                this.authorFilter.add(book);
            }
        }
    }

    /**
     * Returns the string used as the author filter, null if no author
     * filter is currently set.
     *
     * @return the string used as the author filter, or null if none is set
     */
    @Override
    public String getAuthorFilter() {
        return this.filterBy;
    }

    /**
     * Resets the author filter to null (no filter).
     */
    @Override
    public void resetAuthorFilter() {
        this.filterBy = null;
        this.authorFilter.clear();
    }

    /**
     * Search through all the books in the title base and return books whose
     * title contains the string word (and that satisfies the author filter,
     * if an author filter is set).
     *
     * @param word word that must be contained in a book's title in result set
     * @return list of books found
     */
    @Override
    public List<IBook> searchByTitleWord(String word) {
        // Define a new list to store books found
        List<IBook> result = new ArrayList<>();

        // Search using iterator
        if (this.filterBy == null) {
            // authorFilter is not set
            for (IBook book: this.database) {
                // Match in a non-sensitive way
                if (book.getTitle().toLowerCase().contains(word.toLowerCase()))
                    result.add(book);
            }
        } else {
            // authorFilter is set
            for (IBook book : this.authorFilter) {
                // Match in a non-sensitive way
                if (book.getTitle().toLowerCase().contains(word.toLowerCase()))
                    result.add(book);
            }
        }

        return result;
    }

    /**
     * Return the book uniquely identified by the ISBN, or null if ISBN is not
     * present in the dataset.
     *
     * @param ISBN the book's ISBN number
     * @return the book identified by the ISBN, or null if ISBN not in database
     */
    @Override
    public IBook getByISBN(String ISBN) {
        return this.database.get(ISBN);
    }
}
