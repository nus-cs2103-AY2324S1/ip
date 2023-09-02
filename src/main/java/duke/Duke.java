package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.list.FunnyList;

/**
 * Main class for the Duke application.
 * This class initializes and manages the Duke application. It handles user interaction,
 * task processing, and exception handling.
 */
public class Duke {
    private Storage storage;
    private FunnyList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path to the text file to load and save task data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new FunnyList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new FunnyList();
        }
    }

    /**
     * Main method to start the Duke application.
     *
     * @param args Command-line arguments (Not applicable).
     */
    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

    /**
     * Runs the main loop of the Duke application.
     * Processes user commands and handles exceptions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.printLine();
            }
        }
    }
}
