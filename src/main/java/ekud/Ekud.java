package ekud;

import ekud.exceptions.EkudException;
import ekud.exceptions.EkudIOException;
import ekud.parser.Parser;
import ekud.storage.Storage;
import ekud.tasks.TaskList;
import ekud.ui.Launcher;

/**
 * The ekud.Ekud chatbot where the main functionality of this program runs in.
 */
public class Ekud {
    // TaskList object to store and manipulate all of user's tasks, as well as
    // handle invalid inputs
    private final TaskList taskList;
    // Storage object to handle loading / saving of tasks between the tasks
    // in TaskList and the tasks stored on the hard disk
    private final Storage storage;
    private boolean isActive;

    /**
     * Constructor for the ekud.Ekud chatbot to intitialise its taskList.
     */
    public Ekud() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/savedTasks.txt");
        this.isActive = true;
    }

    /**
     * Main function for chatbot to parse user input, execute the command,
     * then return a String response for the user.
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser();
        // Process user input
        String[] inputParts = parser.parseInput(userInput);
        String userCommand = inputParts[0];
        String userArgs = inputParts[1];
        // Handle start and end commands
        if (userCommand.equals("start")) {
            if (isActive) {
                return "Ekud is already running :>";
            }
            isActive = true;
            return this.loadData() + "\n" + this.getGreeting();
        }
        if (!isActive) {
            return "Ekud is currently sleeping... (Type 'start' to run again)";
        }
        if (userCommand.equals("end")) {
            this.isActive = false;
            return this.saveData() + "\nGoodbye, have a nice day! :p";
        }
        // Handle main commands
        try {
            // commands will elicit a response from the taskList object
            return parser.parseAndExecute(userCommand, userArgs, this.taskList);
        } catch (EkudException e) {
            return e.toString(); // catch and return the EkudException error message
        }

    }
    public String loadData() {
        try {
            return this.storage.loadData(this.taskList);
        } catch (EkudIOException e) {
            return e.toString();
        }
    }

    public String saveData() {
        try {
            return this.storage.saveData(this.taskList);
        } catch (EkudIOException e) {
            return e.toString();
        }
    }

    public String getGreeting() {
        return "Hello there! I'm ekud. :)\n"
                + "What can I do for you? :O";
    }

    // Loads tasklist then launches the GUI for the chatbot
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
