package duke;

import duke.command.Command;
import duke.error.DukeException;
import duke.lib.UI;
import duke.parser.Parser;
import duke.lib.Storage;
import duke.task.TaskList;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    public Duke(String filePath) {
        this.ui = new UI();
        this.storage = new Storage("data", "duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

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
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

}
