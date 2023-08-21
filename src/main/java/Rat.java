import java.util.Scanner;
import rat.print.RatPrinter;
import rat.storage.*;
import rat.inputs.RatInput;

/**
 * This class encapsulates my version of Duke, called Rat.
 * Rat is a chatbot that helps the user keep track of their tasks.
 * @version Week-2
 * @author Keagan
 */
public class Rat {

    /**
     * The RatStorage object used to store and process the user's tasks.
     */
    static RatStorage ratStorage;

    /**
     * The RatInput Object used to process user input.
     */
    static RatInput ratInput;

    /**
     * The Scanner object used to read user input.
     */
    static Scanner sc;

    /**
     * Initialises the Rat program.
     * Instantiates a RatStorage object, Scanner object, and a RatInput Object.
     */
    public static void initialise() {
        RatPrinter.printWelcome();
        ratStorage = new RatStorage();
        sc = new Scanner(System.in);
        ratInput = new RatInput(sc, ratStorage);
    }

    /**
     * The main method of Rat.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Rat.initialise();
        ratInput.handleInput();
        sc.close();
    }
}


