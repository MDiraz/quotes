package quotes;

import java.io.*;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.*;

public class QuoteDOMWriter {
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;

    // XML file that has quotes
    private static final String quoteFilename = "quotes/quotes.xml";

    public QuoteDOMWriter() {
        try {
            this.dbFactory = DocumentBuilderFactory.newInstance();
            this.dBuilder = dbFactory.newDocumentBuilder();
            // create the doc which will hold the xml file
            this.doc = dBuilder.newDocument();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean writeNewQuote(QuoteList oldQuoteList, String newQuoteText, String newAuthor) {
        // Check integrity of quote and author before commencing
        if (checkValidQuote(newQuoteText) && checkValidAuthor(newAuthor)) {

            // add element to document
            Element root = doc.createElement("quote-list");

            // add old quotes to xml file
            for (int i = 0; i < oldQuoteList.getSize(); i++) {
                String author = oldQuoteList.getQuote(i).getAuthor();
                String quoteText = oldQuoteList.getQuote(i).getQuoteText();
                this.createQuote(doc, root, quoteText, author);
            }
            // add new quote to xml file
            this.createQuote(doc, root, newQuoteText, newAuthor);

            // add root to the doc
            doc.appendChild(root);

            try {
                // write data to XML using Transformer factory 
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                // specify properties of xml file
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");

                // dom source si 
                DOMSource source = new DOMSource(doc);

                //create the new XML file (which replaces old xml file)
                StreamResult file = new StreamResult(new FileOutputStream(quoteFilename));

                //write data to the new XML file 
                transformer.transform(source, file);
                return true; // return true when quote is added successfully
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false; // error occured with writing 
            }
        }
        return false; // error with integrity of quote/author
    }

    private void createQuote(Document doc, Element root, String quoteText, String author) {
        // create quote tag
        Element quoteElement = doc.createElement("quote");

        // create quote-text tag element and add its text content
        Element quoteTextElement = doc.createElement("quote-text");
        quoteTextElement.appendChild(doc.createTextNode(quoteText));

        // create author tag element and add its text content
        Element authorElement = doc.createElement("author");
        authorElement.appendChild(doc.createTextNode(author));

        // add quote-text and author elements to quote 
        quoteElement.appendChild(quoteTextElement);
        quoteElement.appendChild(authorElement);

        // append quote to root
        root.appendChild(quoteElement);
    }

    // Check integrity of input quote
    private boolean checkValidQuote(String quoteText) {
        return startsWithUpperCase(quoteText) && longerThanTwoWords(quoteText) && endsWithPunctuation(quoteText);
    }

    // Check integrity of input author by checking 
    // that all words in author name are capitalized
    private boolean checkValidAuthor(String author) {
        String[] name = author.split(" ");
        for (int i = 0; i < name.length; i++) {
            if (startsWithUpperCase(name[i]) == false) {
                return false;
            }
        }
        return true;
    }

    // Check input starts with a capital letter
    private boolean startsWithUpperCase(String input) {
        return Character.isUpperCase(input.charAt(0));
    }

    // Check input quoteText is longer than 2 words
    private boolean longerThanTwoWords(String quoteText) {
        return (quoteText.split(" ").length > 2);
    }

    // Check input quoteText ends with a punctuation
    private boolean endsWithPunctuation(String quoteText) {
        char lastChar = quoteText.charAt(quoteText.length() - 1);
        return (lastChar == '.' || lastChar == '!' || lastChar == '?');
    }
}