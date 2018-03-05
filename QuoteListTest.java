import org.junit.Test;
import quotes.QuoteSaxParser;

import static org.junit.Assert.*;

public class QuoteListTest {

    // Test if searching for quotes via author works
    @Test
    public void searchTest1() {
        String searchTerm = "Nixon";
        int searchMode = 0;
        String expected = "Quote {author='Richard Nixon', quoteText='I know that you believe you understand"
                + " what you think I said, but I am not sure you realize that what you heard is not what I meant.'}";
        quotes.QuoteSaxParser qParser = new QuoteSaxParser("./quotes.xml");
        quotes.QuoteList quoteList = qParser.getQuoteList();
        quotes.Quote searchResult = quoteList.search(searchTerm, searchMode).getQuote(0);
        assertEquals(expected, searchResult.toString());
    }

    // Test if search for quotes via content works
    @Test
    public void searchTest2() {
        String searchTerm = "obfuscation";
        int searchMode = 1;
        String expected = "Quote {author='Don Cunningham', quoteText='Eschew obfuscation!'}";
        quotes.QuoteSaxParser qParser = new QuoteSaxParser("./quotes.xml");
        quotes.QuoteList quoteList = qParser.getQuoteList();
        quotes.Quote searchResult = quoteList.search(searchTerm, searchMode).getQuote(0);
        assertEquals(expected, searchResult.toString());
    }

    // Test searching for quotes using both author and content
    @Test
    public void searchTest3() {
        String searchTerm = "ni";
        int searchMode = 2;
        String expected1 = "Quote {author='Richard Nixon', quoteText='I know that you believe you understand"
                + " what you think I said, but I am not sure you realize that what you heard is not what I meant.'}";
        String expected2 = "Quote {author='Don Cunningham', quoteText='Eschew obfuscation!'}";
        quotes.QuoteSaxParser qParser = new QuoteSaxParser("./quotes.xml");
        quotes.QuoteList quoteList = qParser.getQuoteList();
        quotes.QuoteList searchResults = quoteList.search(searchTerm, searchMode);
        assertTrue(2 == searchResults.getSize());
        quotes.Quote quote1 = searchResults.getQuote(0);
        quotes.Quote quote2 = searchResults.getQuote(1);
        assertTrue(expected1.equals(quote1.toString()) || expected1.equals(quote2.toString()));
        assertTrue(expected2.equals(quote1.toString()) || expected2.equals(quote2.toString()));
    }

    // Test searching quotes if search result is empty
    @Test
    public void searchTest4() {
        String searchTerm = "trucks";
        int searchMode = 2;
        quotes.QuoteSaxParser qParser = new QuoteSaxParser("./quotes.xml");
        quotes.QuoteList quoteList = qParser.getQuoteList();
        quotes.QuoteList searchResults = quoteList.search(searchTerm, searchMode);
        assertTrue(0 == searchResults.getSize());
    }
}