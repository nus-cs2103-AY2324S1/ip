package Duke;

/**
 * Main class for running the Duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Duke class.
     * Initializes the user interface, storage, task list, and parser.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("file.txt");
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Gets a response to user input and performs necessary actions.
     *
     * @param input The user's input.
     * @return The response generated based on the input.
     */
    public String getResponse(String input) {
        assert !input.isEmpty();
        parser.parse(input, tasks, storage, ui);
        if (parser.isExit()) {
            ui.showExit();
        }
        return ui.emptyBuffer();
    }

    /**
     * Checks if the Duke program has ended (i.e., the user requested an exit).
     *
     * @return true if the program has ended, false otherwise.
     */
    public boolean hasEnded() {
        return parser.isExit();
    }
}
