package anya;

import anya.exception.AnyaException;
import anya.parser.Parser;
import anya.storage.Storage;
import anya.task.TaskList;
import anya.ui.Ui;

/**
 * Represents a chat bot named anya.
 */
public class Anya {
    private static final String STORAGE_FILE_PATH = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new instance of the {@code Anya} class with the specified storage file path.
     * This constructor initializes the user interface (UI), storage, and task list parser for the Anya application.
     * It also loads tasks from the specified storage file and handles exceptions related to storage operations.
     *
     * @throws RuntimeException If there is an issue with the storage file path or storage operation,
     *                          a runtime exception is thrown, and an initialization error message is
     *                          displayed through the UI.
     */
    public Anya() {
        try {
            this.ui = new Ui();
            this.storage = new Storage(STORAGE_FILE_PATH);
            this.tasks = new TaskList(storage.load());
            this.parser = new Parser(storage, tasks);
        } catch (AnyaException e) {
            ui.showInitError();
        }
    }

    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (AnyaException e) {
            return e.getMessage();
        }
    }
}
