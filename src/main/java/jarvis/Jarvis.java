package jarvis;

import jarvis.command.Command;
import jarvis.exception.JarvisException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents the main Jarvis application.
 * Initializes the application and starts the interaction with the user.
 */
public class Jarvis {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private boolean isExit;

    /**
     * Constructs a Jarvis object.
     *
     * @param filePath The file path of the file to store the tasks.
     */
    public Jarvis(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";

        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        this.parser = new Parser();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = parser.parse(fullCommand);
            String response = c.execute(tasks, ui, storage);
            this.isExit = c.isExit();
            return response;
        } catch (JarvisException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the greeting message to be displayed to the user.
     *
     * @return The greeting message to be displayed to the user.
     */
    public String getGreeting() {
        return ui.greet();
    }

    public boolean hasExited() {
        return this.isExit;
    }
}
