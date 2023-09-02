package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Main class that drives the Duke chatbot.
 */
public class Duke {

    public Storage storage;
    protected TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui(this);
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.readFromDatabase(), this);
        } catch (DukeException e) {
            this.ui.showError(e);
            this.tasks = new TaskList(new ArrayList<Task>(), this);
        }
    }

    /**
     * Driver function to start the bot on a text-based UI.
     */
    public void run() {
        ui.printIntro();
        ui.promptInput();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
