package rat;

import static rat.io.RatPrinter.printWelcome;

import java.util.Scanner;

import rat.io.RatInput;
import rat.storage.RatStorage;
import rat.tasks.RatTaskManager;

/**
 * This class encapsulates my version of Duke, called Rat.
 * Rat is a chatbot that helps the user keep track of their tasks.
 *
 * @author Keagan
 * @version Week-3
 */
public class Rat {

    /**
     * The RatStorage object used to store the user's tasks in local storage.
     */
    private static RatStorage ratStorage;

    /**
     * The RatTaskManager object used to store and process the user's tasks.
     */
    private static RatTaskManager ratTaskManager;

    /**
     * The RatInput Object used to process user input.
     */
    private static RatInput ratInput;

    /**
     * The Scanner object used to read user input.
     */
    private static Scanner sc;

    /**
     * Initialises the Rat program.
     * Instantiates a RatTaskManager object, Scanner object, and a RatInput Object.
     */
    public static void initialise() {
        printWelcome();
        ratStorage = new RatStorage("data/rat.txt");
        ratTaskManager = new RatTaskManager(ratStorage);
        sc = new Scanner(System.in);
        ratInput = new RatInput(sc, ratTaskManager);
    }

    public String getResponse(String input) {
        return ratInput.handleInput(input);
    }

    /**
     * The main method of Rat.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Rat.initialise();
        ratInput.parseInputs();
        sc.close();
    }
}


