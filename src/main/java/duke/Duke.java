package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeFormatter;

/**
 * Duke is a simple task management application that allows users to keep track
 * of tasks, including to-dos, deadlines, and events.
 *
 * Duke uses a command-based approach, where users enter text commands to
 * perform various operations on the task list.
 */
public class Duke {

    /**
     * The date and time input format used for parsing date and time strings.
     */
    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    private Storage storage;
    private TaskList items;
    private Ui ui;

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            items = new TaskList(storage.load());
        } catch (DukeException c) {
            ui.showLoadingError();
            items = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     *
     * This method initializes the user interface, loads tasks from storage,
     * and enters a loop to process user commands.
     */
    public void run() {
        ui.greet();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.getInput();
                Command c = Parser.parse(fullCommand);
                c.execute(items, ui, storage);
                isRunning = !c.exitsNext();
            } catch (DukeException e) {
                ui.talk(e.getMessage());
            }
        }
    }

    /**
     * The main method that starts the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
