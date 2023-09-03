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
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | DateTimeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point for starting the Duke chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
