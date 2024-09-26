package jeoe;

import jeoe.Commands.Command;
import jeoe.Commands.CommandParser;
import jeoe.Exceptions.InitializationException;
import jeoe.Exceptions.InvalidCommandException;
import jeoe.Exceptions.NoCommandException;
import jeoe.Exceptions.RunException;
import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

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
     * Constructor for a Jeoe object.
     *
     * The Jeoe object is to be passed to the Main class which runs the entire application.
     * The Jeoe object process the input using all its other components and passes back the
     * result to the Main class for the JavaFX components to render out the output on a GUI.
     */
    public Jeoe() {
        try {
            Jeoe.initialize();
        } catch (InitializationException e) {
            // exception to do with initialization => scanner fails (cannot be file issue)
            System.out.println(e.getMessage()); // can change to ui printing this out in the future
        }
    }

    /**
     * Initialises the Jeoe program.
     *
     * Instantiates a TaskManager object, Ui object, and a StorageManager Object.
     */
    private static void initialize() throws InitializationException {
        try {
            ui = new Ui();
            storageManager = new StorageManager(filePath); // loading tasks & saving tasks
            // contains list of task, and has operations to add/delete tasks
            taskManager = new TaskManager(storageManager.load(), filePath);
            // load passes the string for TM to add task to arr

            ui.showOpenString(); // leave it here so i know that program did get initialize
        } catch (Exception e) {
            System.out.println("Jeoe initialization failed");
        }
    }

    /**
     * Gets the response from the Jeoe program.
     *
     * Gets the response from the Jeoe program to be displayed on the GUI.
     * @param input The input from the user using the GUI.
     * @return Response string to be displayed on the GUI.
     */
    public String getResponse(String input) {
        String jeoeReply;

        try {
            Command c = CommandParser.parse(input);
            jeoeReply = c.executeAndReply(taskManager, ui, storageManager); // already has ui.getReply executed
        } catch (InvalidCommandException e) {
            // print exception, they will handle their formatting themselves
            jeoeReply = ui.getReply(e.getMessage());
        } catch (NoCommandException e) { // can think of if tried 3 empty commands, terminate program
            jeoeReply = ui.getReply(e.getMessage());
        } catch (RunException e) {
            jeoeReply = ui.getReply(e.getMessage());
        }

        assert jeoeReply instanceof String : "jeoe's response has to be a string type";
        return jeoeReply;
    }

    /**
     * The main method of Jeoe.
     * The block of code is to initialize & run the Jeoe program (in the CLI).
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

    }
}
