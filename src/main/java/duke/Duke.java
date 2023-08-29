package duke;

import duke.command.Command;
import duke.exceptions.DukeException;

/**
 * Duke is a simple task management application that allows users to add, delete and list tasks.
 */
public class Duke {
    private Storage storage;
    private DukeList tasks;
    private Ui ui;

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
            ui.showLoadingError();
            tasks = new DukeList();
        }
    }

    /**
     * Runs the main loop of the Duke application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showBye();
    }

    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
