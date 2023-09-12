package duke;

import duke.parser.Parser;

/** Duke is a chat bot that allows users to add, delete, mark, view tasks. */
public class Duke {

    private Storage storage;
    private TaskList list;
    private Ui ui;
    private final String filePath;
    private Parser parser;

    /**
     * Creates a new Duke chat bot
     *
     * @param filePath Path to file to read stored task list.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.storage = new Storage(filePath);
        this.list = new TaskList(storage.load());
        this.parser = new Parser(this.list, filePath);
    }

    /**
     * Gives back a reply to an input from user.
     *
     * @param input The command given by user.
     * @return Reply to respond to user.
     */
    public String respond(String input) {
        try {
            return parser.parse(input).execute();
        } catch (Exception error) {
            return this.ui.showError(error.getMessage());
        }
    }
}
