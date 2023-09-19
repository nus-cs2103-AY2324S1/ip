package duke;

import java.time.format.DateTimeParseException;

import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import javafx.util.Pair;

/**
 * Main class to run BUTTER.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Class constructor for Duke.
     * Initialises the ui, storage and tasks used in for the BUTTER chatbot program.
     *
     * @param filePath the path of the file containing results from previous interactions.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), storage, ui);
        parser = new Parser(tasks, ui);
    }

    /**
     * Returns a pair of the form [message, isErrorMessage].
     * isErrorMessage is true if the response from the chatbot is an error message.
     *
     * @param input user input.
     * @return the response to the user's command.
     */
    public Pair<String, Boolean> getResponse(String input) {
        try {
            return new Pair<>(parser.parseInput(input), false);
        } catch (DukeException e) {
            return new Pair<>(e.getMessage(), true);
        } catch (DateTimeParseException e) {
            return new Pair<>(this.ui.showInvalidDateFormat(), true);
        }
    }
}
