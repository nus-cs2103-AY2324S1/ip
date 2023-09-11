package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.list.TaskList;

/**
 * Main class for the Duke application.
 * This class initializes and manages the Duke application. It handles user interaction,
 * task processing, and exception handling.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isFreshList = false;




    /**
     * Constructs a Duke instance with the specified file path.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            isFreshList = true;
            this.tasks = new TaskList();
        }
    }


    /**
     * Start the Duke application.
     *
     * @param args Command-line arguments (Not applicable).
     */
    public static void main(String[] args) {
        new Duke().run();
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

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String getInitialMessage() {
        return ui.showWelcome() + "\n"
                + (isFreshList ? ui.showLoadingError() : ui.showLoadingSuccess());
    }
}
