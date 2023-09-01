package duke;

import duke.command.Command;
import duke.exception.DukeException;
import java.io.FileNotFoundException;

/**
 * Represents the command line chat application Duke
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a Duke instance with the specified file path.
     * @param filePath File path which the Duke instance reads and writes from.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks =  new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException e) {
            tasks =  new TaskList();
        }
    }
    private void run() {
        Ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }

    /**
     * Main method for the Duke instance.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}