package grumpygordon;

import grumpygordon.commands.Command;
import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
import grumpygordon.exceptions.GrumpyGordonInvalidCommandException;
import grumpygordon.parser.Parser;
import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

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
     * User interface for GrumpyGordon.
     */
    private Ui ui;

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
        this.ui = new Ui(tasks, storage);
    }

    /**
     * Gets GrumpyGordon's response to user input.
     * @param userInput User input
     * @return GrumpyGordon's response
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput, this.tasks);
            return command.execute(tasks, this.ui, storage);
        } catch (GrumpyGordonInvalidCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs GrumpyGordon.
     */
    public void run() {
        this.ui.run();
    }
}
