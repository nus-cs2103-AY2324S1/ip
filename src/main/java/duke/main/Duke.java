package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidFileException;

/**
 * Main entry point for application to run.
 * Duke is a to-do list CLI app, with different kind of tasks.
 * Able to store task data.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes Duke class. Initializes filepath to load and save data,
     * loads data from filepath.
     *
     * @param filePath Filepath to load and save data to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidFileException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs application.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showCommands();
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
    }

    public static void main(String[] args) {
        new Duke("tasks.json").run();
    }
}
