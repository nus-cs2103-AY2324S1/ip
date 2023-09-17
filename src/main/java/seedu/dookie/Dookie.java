package seedu.dookie;

/**
 * Encapsulates the Dookie class
 * Dookie is the chatbot that runs the program.
 */
public class Dookie {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new Dookie instance.
     */
    public Dookie() {
        ui = new Ui();
        storage = new Storage("./data/dookie.txt");

        try {
            tasks = new TaskList(storage.load());
            parser = new Parser(storage, tasks, ui);
        } catch (DookieException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns a string containing Dookie's response to the user input.
     *
     * @param input The command given by the user.
     * @return The result of parsing that command.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

}