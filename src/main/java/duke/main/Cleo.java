package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidFileException;

/**
 * Main entry point for application to run.
 * Duke is a to-do list CLI app, with different kind of tasks.
 * Able to store task data.
 */
public class Cleo {
    public static final int ERROR_MESSAGE_TYPE = 0;
    public static final int INVALID_MESSAGE_TYPE = -1;
    public static final int NORMAL_MESSAGE_TYPE = 1;
    private int messageType = INVALID_MESSAGE_TYPE;
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit = false;

    /**
     * Initializes Duke class. Initializes filepath to load and save data,
     * loads data from filepath.
     *
     * @param filePath Filepath to load and save data to.
     */
    public Cleo(String filePath) {
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
    }

    public static void main(String[] args) {
        new Cleo("tasks.json").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            c.execute(tasks, ui, storage);
            messageType = NORMAL_MESSAGE_TYPE;
            return c.getOutput();
        } catch (InvalidCommandException e) {
            messageType = ERROR_MESSAGE_TYPE;
            return e.getMessage() + "\n" + ui.showCommands();
        } catch (DukeException e) {
            messageType = ERROR_MESSAGE_TYPE;
            return "Error: " + e.getMessage();
        }
    }

    public int getMessageType() {
        return messageType;
    }

    public boolean isExit() {
        return isExit;
    }
}
