package duke;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.command.*;
import duke.parser.Parser;
import duke.exception.DukeException;

/**
 * The main class that runs the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified file path for task storage.
     *
     * @param filePath The path to the file used for task storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Duke chatbot.
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
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

