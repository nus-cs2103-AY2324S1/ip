package rat;

import java.util.Scanner;

import static rat.io.RatPrinter.*;

import rat.tasks.*;
import rat.io.RatInput;

/**
 * This class encapsulates my version of Duke, called rat.Rat.
 * rat.Rat is a chatbot that helps the user keep track of their tasks.
 *
 * @author Keagan
 * @version Week-2
 */
public class Rat {

    /**
     * The RatTaskManager object used to store and process the user's tasks.
     */
    static RatTaskManager ratTaskManager;

    /**
     * The RatInput Object used to process user input.
     */
    static RatInput ratInput;

    /**
     * The Scanner object used to read user input.
     */
    static Scanner sc;

    /**
     * Initialises the rat.Rat program.
     * Instantiates a RatTaskManager object, Scanner object, and a RatInput Object.
     */
    public static void initialise() {
        printWelcome();
        ratTaskManager = new RatTaskManager();
        sc = new Scanner(System.in);
        ratInput = new RatInput(sc, ratTaskManager);
    }

    /**
     * The main method of rat.Rat.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Rat.initialise();
        ratInput.handleInput();
        sc.close();
    }
}


