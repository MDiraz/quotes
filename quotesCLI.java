package quotes;

import java.util.Scanner;

public class quotesCLI
{
    // XML file that has quotes
    private static final String quoteFileName = "quotes/quotes.xml";

    // Stores all the quotes from the xml file
    private static QuoteList quoteList;


    public static void main(String args[])
    {
        QuoteSaxParser qParser = new QuoteSaxParser (quoteFileName);
        quoteList = qParser.getQuoteList();
        Quote randomQuote = quoteList.getRandomQuote();
        Scanner in = new Scanner(System.in);
        QuoteList searchResults;
        while(true){
            System.out.println("\nWelcome to the Quotes Program!");
            System.out.println("Today's Random Quote is:");
            System.out.println(randomQuote.displayQuote());
            System.out.println("\nSelect an option by inserting a number.");
            System.out.println("(1) Get Another Random Quote");
            System.out.println("(2) Find quotes by content");
            System.out.println("(3) Find quotes by author");
            System.out.println("(4) Find quotes by both author & content");
            System.out.println("(0) Exit Program");
            int input = Integer.parseInt(in.next());
            switch (input) {
                case 1:
                    randomQuote = quoteList.getRandomQuote();
                    System.out.println("Random Quote:");
                    System.out.println("\t" + randomQuote.getQuoteText());
                    System.out.println("\t\t–" + randomQuote.getAuthor());
                    break;
                case 2:
                    System.out.println("Insert search terms followed by enter");
                    searchResults = quoteList.search(in.next(), 1);
                    System.out.println("Search Results: ");
                    System.out.println(searchResults.displayQuoteList());
                    break;
                case 3:
                    System.out.println("Insert search terms followed by enter");
                    searchResults = quoteList.search(in.next(), 0);
                    System.out.println("Search Results: ");
                    System.out.println(searchResults.displayQuoteList());
                    break;
                case 4:
                    System.out.println("Insert search terms followed by enter");
                    searchResults = quoteList.search(in.next(), 2);
                    System.out.println("Search Results: ");
                    System.out.println(searchResults.displayQuoteList());
                    break;
                case 0:
                    System.exit(1);
                    break;
                default:
                    System.out.println("Unrecognized Command. Please try again!");
            }
        }

    }
}