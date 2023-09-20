package nexus;

import java.io.File;

import nexus.components.Parser;
import nexus.components.Storage;
import nexus.components.Ui;
import nexus.exceptions.InvalidInputException;
import nexus.task.TaskList;

/**
 * The main class for Nexus ChatBot.
 */
public class Nexus {
    /**
     * OS-Independent path.
     */
    private static final String FILEPATH = String.join(File.separator, "src", "main", "data", "nexus.txt");
    private TaskList list;
    private Storage storage;
    private Ui ui;

    /**
     * Create ChatBot from default data path.
     */
    public Nexus() {
        this.storage = new Storage(FILEPATH);
        this.ui = new Ui();
        this.list = new TaskList(storage.loadTasks());
    }

    /**
     * Generate response to user.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseInput(ui, storage, this.list, input);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }
}
