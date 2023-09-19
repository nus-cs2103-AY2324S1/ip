package duke;

import java.time.DateTimeException;

import duke.command.Command;
import duke.command.UndoCommand;
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
     * @param inputs The user inputs.
     */
    public void run(String... inputs) {
        for (String input : inputs) {
            try {
                Command command = Parser.parse(input.trim());
                command.execute(tasks, ui, storage);
                UndoCommand.saveCommand(command);
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            } catch (DateTimeException e) {
                ui.showError("Error 404!!! Please enter the correct date format (YYYY-MM-DD).");
            }
        }
    }
}
