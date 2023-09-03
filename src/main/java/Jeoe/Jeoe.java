package Jeoe;

import Jeoe.Commands.Command;
import Jeoe.Commands.CommandParser;
import Jeoe.Exceptions.InitializationException;
import Jeoe.Exceptions.InvalidCommandException;
import Jeoe.Exceptions.NoCommandException;
import Jeoe.Exceptions.RunException;
import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;

/**
 * This class encapsulates my version of Duke, called Jeoe.
 * Jeoe is a chatbot that helps the user keep track of their tasks.
 * Java coding standard was adhered to in this program.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Jeoe {

    /** Manager to contain task list and handle operations on tasks. */
    private static TaskManager taskManager;

    /** Manager to format & print statements to the ui. */
    private static Ui ui;

    /** Manager to load & save task from local storage. */
    private static StorageManager storageManager;

    /** Contains file path to local storage containing saved tasks. */
    private static String filePath = System.getProperty("user.dir") + "/storage/taskListData.txt"; // dir is till ip


    /**
     * Initialises the Jeoe program.
     * Instantiates a TaskManager object, Ui object, and a StorageManager Object.
     */
    private static void initialize() throws InitializationException {
        try {
            ui = new Ui();
            storageManager = new StorageManager(filePath); // loading tasks & saving tasks
            // contains list of task, and has operations to add/delete tasks
            taskManager = new TaskManager(storageManager.load()); // load passes the string for TM to add task to arr

            ui.showOpenString();
        } catch (Exception e) {
            System.out.println("Jeoe initialization failed");
        }
    }

    /**
     * Runs the Jeoe program.
     * Sets the loop for the 3 components and executes the commands till a bye command is reached.
     */
    public static void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.nextLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskManager, ui, storageManager);
                isExit = c.isExit();
            } catch (InvalidCommandException e) {
                // print exception, they will handle their formatting themselves
                System.out.println(e.getMessage());
            } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
                System.out.println(e.getMessage());
            } catch (RunException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method of Jeoe.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            Jeoe.initialize();
            Jeoe.run();
        } catch (InitializationException e) {
            // exception to do with initialization => scanner fails (cannot be file issue)
            System.out.println(e.getMessage()); // can change to ui printing this out in the future
        }
    }
}
