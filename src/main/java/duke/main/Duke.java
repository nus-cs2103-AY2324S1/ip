package duke.main;

import duke.command.*;
import duke.exception.*;

import java.io.IOException;

/**
 * The main class for Duke chatbot application.
 */
public class Duke {

    private static final String FILE_PATH = "./tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke object with the given file path.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String initialise() {
        ui.showWelcome();
        return ui.getCurrentMessage();
    }

    /**
     * Runs the chatbot application
     */
    public void run(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponse(String input) {
        run(input);
        return ui.getCurrentMessage();
    }
}
