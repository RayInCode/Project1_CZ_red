import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this class read csv file and parse the data to creat list of Book object
 */
public class BookLoader implements IBookLoader{
    /**
     * this method split lint string from csv and split it into smaller strings with comma outside outer quotes
     *
     * @param line current line string from csv
     * @return a list of String which contains raw tokens awaiting further processing
     */
    private ArrayList<String> splitLine(String line) {
        ArrayList<String> tokens = new ArrayList<String>();
        int beginIndex = 0;
        boolean isInQuote = false;
        for(int currentIndex = 0; currentIndex < line.length(); currentIndex++) {
            if(line.charAt(currentIndex) == '\"')  isInQuote = !isInQuote;
            else if(line.charAt(currentIndex) == ',' && !isInQuote) {
                tokens.add(line.substring(beginIndex, currentIndex));
                beginIndex = currentIndex + 1;
            }
        }

        // last token
        tokens.add(line.substring(beginIndex));
        return tokens;
    }

    /**
     * this method deal with escape quotes and whitespaces in the beginning or end of raw tokens
     *
     * @param tokens  raw tokens just got from splitting line string which is delimited by comma
     */
    private void disposeQuote(List<String> tokens) {
        for(int i = 0; i < tokens.size(); i++) {
            // outer quote
            if(tokens.get(i).startsWith("\"") && tokens.get(i).endsWith("\"")) {
                tokens.set(i, tokens.get(i).substring(1, tokens.get(i).length() - 1));
            }

            // trim
            tokens.set(i, tokens.get(i).strip());

            // handle escape quote
            int index = 0;
            Boolean isFollowQuote = false;
            String currentToken = tokens.get(i);
            while(index < currentToken.length()) {
                if(currentToken.charAt(index) == '\"' && isFollowQuote) {
                    tokens.set(i, currentToken.substring(0, index) + currentToken.substring(index + 1));
                    currentToken = tokens.get(i);
                    isFollowQuote = false;
                    continue;
                }else if(currentToken.charAt(index) == '\"') {
                    isFollowQuote = true;
                    index++;
                    continue;
                }
                isFollowQuote = false;
                index++;
            }
        }
    }


    /**
     * this method aims to find out corresponding column according to given names of target tokens and first line tokens
     *
     * @param tokens list of string which stores first line token names in order
     * @param targetTokensName list of string that contains the names of target tokens
     * @return an array of Integer presenting corresponding column number (starts with 0) for each target token
     */
    private Integer[] getTargetColumn(List<String> tokens, String[] targetTokensName) {
        // If apply ArrayList<Integer>, we can not assign value to list through .set()
        // because we have to add elements to list first before .set()
        // and initialize capacity is different from size(number of added elements) in this context
        Integer[] targetColum = new Integer[targetTokensName.length];
        for(int i = 0; i < targetTokensName.length; i++) {
            for(int j = 0; j < tokens.size(); j++) {
                if(tokens.get(j).toLowerCase().contains(targetTokensName[i])) {
                    targetColum[i] = j;
                    break;
                }
            }
        }
        return targetColum;
    }



    /**
     * This method loads the list of books from a CSV file.
     *
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        ArrayList<IBook> ibookList = new ArrayList<>();
        ArrayList<String > tokens = new ArrayList<>();
        String line;
        int numOfTokensName = 0;
        int numOfColum = 0;
        try(Scanner csvScanner = new Scanner(new File(filepathToCSV))){
            // split and parse first line
            if(csvScanner.hasNextLine()){
                line = csvScanner.nextLine();
                tokens = this.splitLine(line);
                this.disposeQuote(tokens);
                numOfTokensName = tokens.size();
            }
//            System.out.println(tokens.size());
//            for(String s : tokens) {
//                System.out.println(s);
//            }


            String[] targetTokensName = new String[]{ "isbn13", "isbn", "title", "authors", "publication_date",
                    "publisher", "language_code", "num_pages", "ratings_count", "average_rating","text_reviews_count",
                    "bookid"};


            Integer[] targetColum = this.getTargetColumn(tokens, targetTokensName);
//            for(int i = 0; i < targetColum.length; i++) {
//                System.out.println(targetColum[i]);
//            }

            while(csvScanner.hasNextLine()) {
                line = csvScanner.nextLine();
                tokens = this.splitLine(line);
                this.disposeQuote(tokens);
                for(int i = tokens.size() - 1; i < numOfTokensName; i++) {
                    tokens.add("");
                }

                String[] args = new String[targetTokensName.length];
                for(int i = 0; i < targetTokensName.length; i++) {
                    args[i] = tokens.get(targetColum[i]);
                }
                ibookList.add(new Book(targetTokensName, args));
            }
        }
        return ibookList;
    }

    public static void main(String[] args) {
//        List<String[]> field = new ArrayList<>();
//        try(Scanner csvScanner = new Scanner(new File("test.csv"))){
//            // split and parse first line
//            String line = new String();
//            if(csvScanner.hasNextLine()){
//                line = csvScanner.nextLine();
//            }
//
//        }
//        catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }


        BookLoader loader = new BookLoader();
        try {
            loader.loadBooks("books.csv");
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
