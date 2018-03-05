import org.junit.Before;
import org.junit.Test;
import quotes.Quote;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class KeywordsTDDTests {

    quotes.Quote q;
    quotes.QuoteList quoteList;
    String quoteFileName = "./quotes.xml";
    quotes.QuoteList searchResults;
    quotes.QuoteDOMWriter qWriter;


    @Before
    public void setUp(){
        q = new Quote("Test", "Knowledge is power.");
        quotes.QuoteSaxParser qParser = new quotes.QuoteSaxParser(quoteFileName);
        // quoteList has all the quotes
        quoteList = qParser.getQuoteList();
        // quote writer
        qWriter = new quotes.QuoteDOMWriter();
    }
    @Test
    public void testKeywordsExists(){
        assertTrue(q.getKeyword() == "");
    }

    @Test
    public void testAddSingleKeywordToExistingQuote(){
        q.setKeyword("Test");
        assertEquals("Test", q.getKeyword());
    }

    @Test
    public void testCreateNewQuoteWithKeyword() {
        Quote q = new Quote("Test Author", "Knowledge is Power", "Knowledge");
        assertEquals("Knowledge", q.getKeyword());
    }

    @Test
    public void testKeywordIsSingleWord(){
        String out = q.setKeyword("This should fail");
        assertEquals("Keyword has to be a single word!", out);
    }

    @Test
    public void testKeywordNotTooLong(){
        String out = q.setKeyword("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        assertEquals("Keyword must be 15 characters long or less", out);
    }

    @Test
    public void testWriteNewQuoteWithKeywordToXML(){
        String author = "Richard Nixon";
        String quoteText = "I am not a crook!";
        String keyword = "lies";

        assertTrue(qWriter.writeNewQuote(quoteList, quoteText, author, keyword));
    }

    @Test
    public void testSearchForQuotesByKeyword(){
        quotes.QuoteList searchResults = quoteList.search("lies", 3);
        System.out.println(searchResults.displayQuoteList());
        System.out.println(searchResults.getSize());
        assertTrue(searchResults.getSize() == 1);

    }
}
