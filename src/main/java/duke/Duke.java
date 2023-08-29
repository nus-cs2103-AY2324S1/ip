package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;

import java.nio.file.Paths;

/**
 * Represents a Duke chat-bot which can store and manage tasks.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates a Duke chat-bot object.
     *
     * @param filePath The path to the file to store the tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (DukeStorageException e) {
            ui.showErrorMessage(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chat-bot.
     */
    public void run() {
        boolean isRunning = true;

        ui.showWelcomeMessage();
        if (tasks.getSize() != 0) {
            ui.showLoadedTasksMessage();
        }

        while (isRunning) {
            try {
                Command command = Parser.parse(ui.readInput());
                command.execute(tasks, ui, storage);
                isRunning = !command.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt").toAbsolutePath().toString()).run();
    }
}
