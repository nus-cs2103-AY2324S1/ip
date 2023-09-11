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
 * @version Week-4
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
     * The file path of the file to be read from and written to.
     */
    private static String filePath = "data/rat.txt";

    /**
     * Constructor for a Rat object.
     */
    public Rat() {
        initialise();
    }

    /**
     * Initialises the Rat program.
     * Instantiates a RatTaskManager object, Scanner object, and a RatInput Object.
     */
    public static void initialise() {
        printWelcome();
        ratStorage = new RatStorage(filePath);
        ratTaskManager = new RatTaskManager(ratStorage);
        sc = new Scanner(System.in);
        ratInput = new RatInput(sc, ratTaskManager);
    }

    /**
     * Returns the response to the user's input.
     * @param input The user's input.
     * @return The response to the user's input generated from respective commands.
     */
    public String getResponse(String input) {
        return ratInput.handleInput(input);
    }

    /**
     * Returns the welcome message of Rat.
     * @return The welcome message of Rat.
     */
    public String getWelcome() {
        return "Hello! I'm Rat! What can I do for you? Type \"help\" to see a list of commands";
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


