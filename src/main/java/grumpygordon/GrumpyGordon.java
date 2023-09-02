package grumpygordon;

import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
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
     * Runs GrumpyGordon.
     */
    public void run() {
        this.ui.run();
    }

    /**
     * Main loop for GrumpyGordon.
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args) {
        try {
            new GrumpyGordon().run();
        } catch (GrumpyGordonException e) {
            System.out.println(e.getMessage());
        }
    }
}
