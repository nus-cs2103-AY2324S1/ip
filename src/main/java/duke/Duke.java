/**
 * ip Project duke.Duke Chat bot
 *
 * @author Aaron Tay
 * @since 2023-08-24
 */
package duke;

import java.io.IOException;

import command.Command;
import exception.DukeException;

/**
 * Duke class instantiates a new Chat Bot.
 */
public class Duke {

    /** Reads and writes task data into file */
    private Storage storage;
    /** List of tasks input by user or loaded from file */
    private TaskList tasks;
    /** Handles user interactions */
    private Ui ui;

    /**
     * Constructs a duke.Duke object with the specified file path.
     *
     * @param filePath The path where the ChatBot loads data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response to the user input. Parses the user input and initialise a new Command,
     * the Command is then executed to return the correct response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }

}
