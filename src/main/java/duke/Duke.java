package duke;

import duke.command.Command;
import duke.exceptions.DukeException;

/**
 * Duke is a simple task management application that allows users to add, delete, and list tasks.
 */
public class Duke {
    final Storage storage;
    final Ui ui;
    private DukeList tasks;

    /**
     * Constructs a Duke object with the specified file path for data storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new DukeList(storage.load());
        } catch (DukeException e) {
            tasks = new DukeList();
        }
    }

    /**
     * Get the response to a user input.
     *
     * @param input The user input.
     * @return A response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
