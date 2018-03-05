package quotes;

import java.util.ArrayList;

/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quote {
    private String author;
    private String quoteText;
    private String keyword;





    // Default constructor does nothing
    public Quote() {
    }

    // Constructor that assigns both author and quote text
    public Quote(String author, String quoteText) {
        this.author = author;
        this.quoteText = quoteText;
        this.keyword = "";
    }

    // Constructor that has author, quote text and quote keywords as input
    public Quote(String author, String quoteText, String keyword) {
        this.author = author;
        this.quoteText = quoteText;
        this.keyword = keyword;
    }

    // Getter and setter for author
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter and setter for quoteText
    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    @Override
    public String toString() {
        return "Quote {" + "author='" + author + '\'' + ", quoteText='" + quoteText + '\'' + '}';
    }

    // Displays quote in a reader-friendy representation
    public String displayQuote() {
        return "\t" + this.getQuoteText() + "\n\t\t–" + this.getAuthor();
    }

    public String getKeyword() {
        return keyword;
    }
    public String setKeyword(String keyword) {
        if (keyword.contains(" ")){
            return "Keyword has to be a single word!";
        }
        if (keyword.length() > 15){
            return "Keyword must be 15 characters long or less";
        }
        this.keyword = keyword;
        return "Keyword added successfully!";
    }
}
