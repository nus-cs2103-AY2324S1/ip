package grumpygordon;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.*;
import grumpygordon.ui.*;
import grumpygordon.exceptions.*;

/**
 * GrumpyGordon Chatbot
 */
public class GrumpyGordon {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public GrumpyGordon() throws GrumpyGordonException {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadTasks();
        } catch (GrumpyGordonInitialisationException e) {
            this.tasks = new TaskList();
        }
        this.ui = new Ui(tasks, storage);
    }

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


