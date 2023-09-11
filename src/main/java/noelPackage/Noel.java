package noelPackage;

import noelPackage.exceptions.NoelException;
import noelPackage.helper.Parser;
import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;
import noelPackage.helper.Ui;

/**
 * Noel is a chat application that helps manage tasks.
 */
public class Noel {

    /**
     * Initial hello message when the program starts.
     */
    static String HELLO_MSG = " Hello! I'm Noel!\nWhat can I do for you?";

    /**
     * Goodbye message when the program terminates.
     */
    static String BYE_MSG = " Bye. Hope to see you again soon!";

    /**
     * UI handler for interacting with the user.
     */
    private final Ui ui;

    /**
     * List of tasks to manage.
     */
    private Tasklist tasks;

    /**
     * Parser for interpreting user input.
     */
    private final Parser parser;

    public static boolean updateFromFile = false;

    /**
     * Constructor for the Noel class.
     * Initializes the UI, loads tasks from storage, and sets up the parser.
     *
     * @param filePath Path to the storage file.
     */
    public Noel(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            System.out.println("Loading input from file...");
            updateFromFile = true;
            tasks = new Tasklist(storage.loadFile());
            System.out.println("File successfully loaded!");
        } catch (NoelException e) {
            System.out.println("Error loading file!");
            ui.showLoadingError();
            tasks = new Tasklist();
        } finally {
            storage.updateFile();
            parser = new Parser(tasks, storage);
            updateFromFile = false;
        }
    }

    /**
     * Fetches the next line of input from the user through the UI.
     *
     * @return A string representing the next line of user input.
     */
    public String chatHelper() {
        return this.ui.getNextLine();
    }

    /**
     * Prints a message to the console with a standardized format.
     *
     * @param message The message to print.
     */
    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    /**
     * The main loop for running the chat application.
     */
    public void run() {
        printFunction(HELLO_MSG);
        String nextLine = chatHelper();
        int commandOption = parser.parseCommand(nextLine);
        while (commandOption != -1) {
            nextLine = ui.getNextLine();
            commandOption = parser.parseCommand(nextLine);
        }
        printFunction(BYE_MSG);
    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Noel("./data/noel.txt").run();
    }
}
