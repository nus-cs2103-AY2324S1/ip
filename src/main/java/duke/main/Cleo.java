package duke.main;

import duke.command.Command;
import duke.exception.*;

/**
 * Main entry point for application to run.
 * Duke is a to-do list CLI app, with different kind of tasks.
 * Able to store task data.
 */
public class Cleo {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private int messageType = INVALID_MESSAGE_TYPE;
    public static final int ERROR_MESSAGE_TYPE = 0;
    public static final int NORMAL_MESSAGE_TYPE = 1;
    public static final int INVALID_MESSAGE_TYPE = -1;

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
        boolean isExit = false;
        while (!isExit) {
            try {
                //ui.showCommands();
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
            c.execute(tasks, ui, storage);
            messageType = NORMAL_MESSAGE_TYPE;
            return c.getOutput();
        } catch (InvalidCommandException e) {
            messageType = ERROR_MESSAGE_TYPE;
            return ui.showCommands();
        }
        catch (DukeException e) {
            messageType = ERROR_MESSAGE_TYPE;
            return "Error: " + e.getMessage();
        }
    }

    public int getMessageType() {
        return messageType;
    }
}
