package duke;

import duke.exception.DukeException;

import java.time.format.DateTimeParseException;

/**
 * A chatbot that interacts with users.
 */
public class Duke {
    /** A list to keep track of the tasks. */
    private TaskList tasks;

    /** The Storage that the Duke uses. */
    private Storage storage;

    /** The Ui that the Duke uses. */
    private Ui ui;

    /** The Parser that the Duke uses. */
    private Parser parser;

    /**
     * Constructs a new Duke that deals with the file at the corresponding path.
     *
     * @param filepath Path to the file.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.tasks = new TaskList(storage.loadFromFile());
        } catch (DukeException e) {
            this.ui.sendMessage(e.getMessage());
            this.tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks, this.ui);
    }

    /**
     * Returns a String as a response to the user input.
     */
    public String getResponse(String input) {
        String res;
        try {
            res = parser.parse(input);
            storage.storeToFile(tasks.toStringInFile());
        } catch (DukeException e) {
            res = ui.sendMessage(e.getMessage());
        } catch (DateTimeParseException f) {
            res = ui.sendMessage("Please follow this format: YYYY-MM-DD or YYYY-MM-DD HH:mm");
        }
        return res;
    }
}
