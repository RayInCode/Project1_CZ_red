public class Book implements IBook{
    private String isbn13;
    private String isbn;
    private String title;
    private String authors;
    private String publicationDate;
    private String publisher;
    private String languageCode;
    private String numPages;
    private String ratingsCount;
    private String averageRating;
    private String textReviewsCount;
    private String bookID;




    public Book(String title, String author, String isbn13) {
        this.title = title;
        this.authors = author;
        this.isbn13 = isbn13;

    }

    /**
     * Construct with given token name and corresponding value
     *
     * @param targetTokens An array of String stores token name
     * @param args An array of String stores corresponding value
     */
    public Book(String[] targetTokens, String[] args) {
        // TODO: check whether length of both array are equal
        for(int i = 0; i < targetTokens.length; i++) {
            switch(targetTokens[i].toLowerCase()) {
                case "isbn13":
                    this.isbn13 = args[i];
                    break;
                case "isbn":
                    this.isbn = args[1];
                    break;
                case "title":
                    this.title = args[i];
                    break;
                case "authors":
                    this.authors = args[i];
                    break;
                case "publication_date":
                    this.publicationDate = args[i];
                    break;
                case "publisher":
                    this.publisher = args[i];
                    break;
                case "language_code":
                    this.languageCode = args[i];
                    break;
                case "num_pages":
                    this.numPages = args[i];
                    break;
                case "ratings_count":
                    this.ratingsCount = args[i];
                    break;
                case "average_rating":
                    this.averageRating = args[i];
                    break;
                case "text_reviews_count":
                    this.textReviewsCount = args[i];
                    break;
                case "bookid":
                    this.bookID = args[i];
                    break;
                default:
                    throw new IllegalArgumentException("Unconsidered Token Name");
            }
        }

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
        return this.authors;
    }

    /**
     * Returns the 13 digit ISBN (ISBN13) that uniquely identifies this book.
     *
     * @return ISBN number of book
     */
    @Override
    public String getISBN13() {
        return this.isbn13;
    }
}
