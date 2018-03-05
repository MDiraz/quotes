package quotes;

import java.util.Scanner;

public class quotesCLI {
    // XML file that has quotes
    private static final String quoteFileName = "./quotes.xml";

    // Stores all the quotes from the xml file
    private static QuoteList quoteList;

    // Main method provides CLI for program
    public static void main(String args[]) {
        QuoteSaxParser qParser = new QuoteSaxParser(quoteFileName);
        // quoteList has all the quotes
        quoteList = qParser.getQuoteList();
        // quote writer
        QuoteDOMWriter qWriter = new QuoteDOMWriter();
        //Random quote of the day
        Quote randomQuote = quoteList.getRandomQuote();
        //Scanner to scan user input
        Scanner in = new Scanner(System.in);
        QuoteList searchResults;
        while (true) {
            //Menu keeps getting printed unless user exits program
            System.out.println("\nWelcome to the Quotes Program!");
            System.out.println("Today's Random Quote is:");
            System.out.println(randomQuote.displayQuote()); //random quote is displayed
            System.out.println("\nSelect an option by inserting a number.");
            System.out.println("(1) Get Another Random Quote");
            System.out.println("(2) Find quotes by content");
            System.out.println("(3) Find quotes by author");
            System.out.println("(4) Find quotes by both author & content");
            System.out.println("(5) Find quotes using a keyword");
            System.out.println("(6) Add new quote");
            System.out.println("(0) Exit Program");
            int input = Integer.parseInt(in.nextLine());
            switch (input) {
            // Display new random quote
            case 1:
                randomQuote = quoteList.getRandomQuote();
                System.out.println("Random Quote:");
                System.out.println("\t" + randomQuote.getQuoteText());
                System.out.println("\t\t–" + randomQuote.getAuthor());
                break;

            // Search quotes by content and display results
            case 2:
                System.out.println("Insert search terms followed by enter");
                searchResults = quoteList.search(in.nextLine(), 1);
                System.out.println("Search Results: ");
                System.out.println(searchResults.displayQuoteList());
                break;

            // Search quotes by author name and display results
            case 3:
                System.out.println("Insert search terms followed by enter");
                searchResults = quoteList.search(in.nextLine(), 0);
                System.out.println("Search Results: ");
                System.out.println(searchResults.displayQuoteList());
                break;

            // Search quotes by content and author name and display results
            case 4:
                System.out.println("Insert search terms followed by enter");
                searchResults = quoteList.search(in.nextLine(), 2);
                System.out.println("Search Results: ");
                System.out.println(searchResults.displayQuoteList());
                break;

            // Search quotes by keywords and display results
            case 5:
                System.out.println("Insert one keyword followed by enter");
                searchResults = quoteList.search(in.nextLine(), 3);
                System.out.println("Search Results: ");
                System.out.println(searchResults.displayQuoteList());
                break;

            // Add new quote to XML file
            case 6:
                System.out.println("Insert the text of the quote");
                String quoteText = in.nextLine();
                System.out.println("Insert the name of the author");
                String author = in.nextLine();
                System.out.println("Insert a keyword for the quote (optional)");
                String keyword = in.nextLine();

                if (keyword.length() <= 1) {
                    keyword = "";
                }
                if (qWriter.writeNewQuote(quoteList, quoteText, author, keyword)) {
                    System.out.println("Quote successfully saved!");
                } else {
                    System.out.print("Error: Ensure proper Capitalization for quote and author. ");
                    System.out.println("Also ensure quote ends in proper punctuation.");
                }
                break;

            // Exit program
            case 0:
                System.exit(1);
                break;

            default:
                System.out.println("Unrecognized Command. Please try again!");
            }
        }

    }
}