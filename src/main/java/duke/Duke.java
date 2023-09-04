package duke;

import java.time.DateTimeException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main class for the chatbot application.
 * Duke is a chatbot that help to manage tasks;
 */
public class Duke {
    public final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Duke instance with the provided file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = this.storage.loadData();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, displaying welcome messages and processing user commands.
     *
     * @param input The user input.
     */
    public void run(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException | DateTimeException e) {
            ui.showError(e.getMessage());
        }
    }
}
