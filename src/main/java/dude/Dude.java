package dude;

import dude.command.DudeCommand;
import dude.exception.DudeException;
import dude.task.TaskList;

/**
 * Dude (Duke, but renamed).
 */
public class Dude {
    private static final String LOGO =
            " _|    _| _    O\n" +
                    "(_||_|(_|(/_  /İ\\\n" +
                    "------------  ```\n";
    private static final String HELLO_MSG = LOGO +
            "Hello! I'm dude.\n" +
            "What can I do for you?";
    private final String filePath;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Dude.
     *
     * @param filePath Path to save file on disk.
     */
    public Dude(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Initializes Dude.
     *
     * @return Output to send to chat.
     */
    public String initialize() {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
            return HELLO_MSG;
        } catch (DudeException e) {
            tasks = new TaskList();
            return e.getMessage();
        }
    }

    /**
     * Reads user input and returns response to it.
     *
     * @param input User input.
     * @return String response to user input.
     */
    public String getResponse(String input) {
        try {
            DudeCommand c = Parser.parse(input);
            assert c != null : "Parsed command cannot be null";
            return c.execute(tasks, storage);
        } catch (DudeException e) {
            return e.getMessage();
        }
    }
}
