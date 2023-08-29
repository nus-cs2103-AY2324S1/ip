package duke;

import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeStorageException;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

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

    public static void main(String[] args) {
        new Duke(Paths.get("data", "duke.txt").toAbsolutePath().toString()).run();
    }

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
}
