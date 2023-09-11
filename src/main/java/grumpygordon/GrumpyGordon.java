package grumpygordon;

import grumpygordon.commands.Command;
import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
import grumpygordon.exceptions.GrumpyGordonInvalidCommandException;
import grumpygordon.parser.Parser;
import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import javafx.application.Application;

/**
 * GrumpyGordon Chatbot
 */
public class GrumpyGordon {
    /**
     * Storage for GrumpyGordon.
     */
    private Storage storage;

    /**
     * List of tasks for GrumpyGordon.
     */
    private TaskList tasks;

    /**
     * Constructor for GrumpyGordon.
     * @throws GrumpyGordonException If GrumpyGordon fails to initialise
     */
    public GrumpyGordon() throws GrumpyGordonException {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTasks();
        } catch (GrumpyGordonInitialisationException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets GrumpyGordon's response to user input.
     * @param userInput User input
     * @return GrumpyGordon's response
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput, this.tasks);
            return command.execute(tasks, storage);
        } catch (GrumpyGordonInvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Main method for GrumpyGordon.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
