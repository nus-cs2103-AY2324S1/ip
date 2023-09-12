package seedu.duke;

/**
 * Encapsulates the Duke class
 * Duke is the chatbot that runs the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");

        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(storage, tasks, ui);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns a string containing Duke's response to the user input.
     *
     * @param input The command given by the user.
     * @return The result of parsing that command.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}