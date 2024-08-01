package jarvis;

import command.Parser;
import services.bizerrors.JarvisException;
import services.tasklist.Storage;
import services.tasklist.TaskList;

/**
 * Represents a personal assistant chatbot that helps the user keep track of different types of tasks.
 */
public class Jarvis {
    private TaskList taskList;
    private Parser parser;

    /**
     * Creates a new Jarvis object with the given data file path to store the list of tasks.
     *
     * @param dataFilePath the path of the data file.
     */
    public Jarvis(String dataFilePath) {
        try {
            taskList = new TaskList(new Storage(dataFilePath));
            parser = new Parser(taskList);
        } catch (JarvisException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the response from Jarvis based on the user input.
     *
     * @param userInput the user input.
     * @return the response from Jarvis.
     */
    public String respond(String userInput) {
        try {
            return parser.parseAndExecute(userInput);
        } catch (JarvisException e) {
            // the errors caught are business errors to be shown to the user.
            return e.toString();
        }
    }


    public String greet() {
        return "At your service, sir.";
    }
}
