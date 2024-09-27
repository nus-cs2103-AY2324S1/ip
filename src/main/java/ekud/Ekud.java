package ekud;

import ekud.exceptions.EkudException;
import ekud.exceptions.EkudIOException;
import ekud.parser.Parser;
import ekud.storage.Storage;
import ekud.tasks.TaskList;
import ekud.ui.Launcher;

/**
 * Represents the Ekud chatbot which encompasses the main functionality of this program.
 */
public class Ekud {
    /**
     * TaskList object to store and manipulate all of user's tasks.
     */
    private final TaskList taskList;
    /**
     * Storage object to handle loading / saving of tasks between the tasks
     * in TaskList and the tasks stored on the hard disk.
     */
    private final Storage storage;
    /**
     * Chatbot status on whether it is currently active or not (to handle main commands).
     */
    private boolean isActive;

    /**
     * Constructor for the chatbot to intitialise its taskList.
     */
    public Ekud() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/savedTasks.txt");
        this.isActive = true;
        assert this.taskList.getSize() == 0 : "Task list should be empty before loading data";
        assert this.storage != null : "Storage should not be null";
        assert this.isActive : "Chatbot should be active";

    }

    /**
     * Returns a String in response to the user input, after parsing the input and executing
     * the given command.
     *
     * @param userInput User input consisting of a command and its arguments.
     * @return String response for the user.
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser();
        // Process user input
        String[] inputParts = parser.parseInput(userInput);
        String userCommand = inputParts[0];
        String userArgs = inputParts[1];
        // Handle start and end commands
        if (userCommand.equals("start")) {
            return this.start();
        }
        if (!isActive) {
            return "Ekud is currently sleeping... (Type 'start' to run again)";
        }
        if (userCommand.equals("end")) {
            return this.end();
        }
        // Else handle main commands
        try {
            // executed commands will elicit a String response from the taskList object
            return parser.parseAndExecute(this.taskList, userCommand, userArgs);
        } catch (EkudException e) {
            return e.toString(); // catch and return error messages for invalid inputs
        }
    }

    /**
     * Activates the chatbot by loading saved data & greeting the user.
     *
     * @return String response.
     */
    public String start() {
        if (isActive) {
            return "Ekud is already running :>";
        }
        isActive = true;
        return this.loadData() + "\n" + this.getGreeting();
    }

    /**
     * De-activates the chatbot by saving the data & responding with a farewell message.
     *
     * @return String response.
     */
    public String end() {
        this.isActive = false;
        return this.saveData() + "\nGoodbye, have a nice day! :p";
    }

    /**
     * Helper function to load saved data upon activating the chatbot with a response
     * on whether data was successfully loaded.
     *
     * @return String response.
     */
    public String loadData() {
        try {
            return this.storage.loadData(this.taskList);
        } catch (EkudIOException e) {
            return e.toString();
        }
    }

    /**
     * Helper function to save data upon de-activating the chatbot with a response on
     * whether data was successfully saved.
     *
     * @return String response.
     */
    public String saveData() {
        try {
            return this.storage.saveData(this.taskList);
        } catch (EkudIOException e) {
            return e.toString();
        }
    }

    /**
     * Retrieves the chatbot's greeting for use in the JavaFX GUI.
     *
     * @return String response.
     */
    public String getGreeting() {
        return "Hello there! I'm ekud. :)\n"
                + "What can I do for you? :O";
    }

    /**
     * Launches the JavaFX GUI for the chatbot.
     *
     * @param args Command line arguments (not used at the moment).
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
