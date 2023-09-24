package duchess;

import java.util.Scanner;
import java.util.function.Consumer;

import duchess.command.Command;

/**
 * The main class used to execute Duchess actions.
 */
public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * An ArrayList to store text in.
     */
    private TaskList storedTasks = new TaskList();

    /**
     * Callback handler, executed after any command is executed.
     * Accepts a string, the String that Duchess prints out.
     */
    private Consumer<String> callbackHandler = null;

    public void setCallbackHandler(Consumer<String> callback) {
        this.callbackHandler = callback;
    }

    /**
     * Executes the callback handler assigned to this Duchess if it is not null.
     *
     * @param s - the String to pass to the callback handler.
     */
    private void executeCallbackHandler(String s) {
        if (this.callbackHandler != null) {
            this.callbackHandler.accept(s);
        }
    }

    /**
     * Attempts to parse and execute the user input as a Duchess command.
     * Afterwards, executes Duchess' set callback handler.
     *
     * @param userInput - the user's input.
     */
    public void parseUserInput(String userInput) {
        Command command = Parser.parseCommand(userInput);
        String output = command.execute(userInput, this.storedTasks);
        this.executeCallbackHandler(output);
    }

    /**
     * Runs the CLI Duchess' main logic loop of receiving user input, and giving the appropriate
     * response.
     */
    public void run() {
        this.executeCallbackHandler(Ui.printGreeting());

        this.loadTasks();

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while (!Parser.isExitCommand(userInput)) {
            System.out.println();
            System.out.print("$>  ");
            userInput = sc.nextLine().trim();
            System.out.println();

            // Check if this command is an exit.
            if (Parser.isExitCommand(userInput)) {
                break;
            }

            parseUserInput(userInput);
        }

        sc.close();

        // Save the tasks.
        this.saveTasks();

        this.executeCallbackHandler(Ui.printFarewell());
    }

    /**
     * Saves Tasks from this Duchess instance into a save file.
     */
    public void saveTasks() {
        Storage.saveTasksToFile(this.storedTasks);
    }

    /**
     * Loads Tasks from a specific save file into this Duchess instance.
     */
    public void loadTasks() {
        // Create the save file, if it does not exist.
        Storage.createSaveFile();

        // Load tasks from the save file.
        this.storedTasks = Storage.loadTasksFromFile();
    }

    public static void main(String[] args) {
        Duchess duchess = new Duchess();
        duchess.setCallbackHandler((s) -> System.out.println(s));
        duchess.run();
    }
}
