package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The main class that initializes and runs the Duke Chat Bot.
 */
public class Duke {
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit = false;
    /**
     * Constructs a Duke instance with the specified data file path.
     */
    Duke() {
        storage = new Storage(DATA_FILE_PATH);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }
    String getResponse(String input) {
        if (isExit) {
            return "";
        }

        try {
            ui.resetOutput();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getOutput();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

