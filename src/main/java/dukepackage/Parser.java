package DukePackage;

import java.util.Scanner;


//function to retrieve string that the user input
/**
 * The Parser class is responsible for retrieving user input from the console.
 */
public class Parser {

    Scanner scanner;

    /**
     * Constructs a new Parser object.
     */
    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retrieves the user input as a string.
     * @return The user input as a string.
     */
    public String getInput() {
        Scanner scanner = this.scanner;

        // Prompt the user for input
        System.out.print(" ");

        // Read and return the user input
        return scanner.nextLine();
    }
}