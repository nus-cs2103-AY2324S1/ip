package duke;
<<<<<<< HEAD

import duke.command.*;
import duke.task.*;
=======
import duke.command.Command;
import duke.task.TaskList;
>>>>>>> branch-A-CodingStandard
import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;

/**
 * The main class that represents the Duke chatbot application.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcome(tasks.getList());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                Command c = Parser.handleInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Constructs a Duke object with the given file path.
     *
     * @param filePath The path to the file for storing task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data/list.txt").run();
    }
}
