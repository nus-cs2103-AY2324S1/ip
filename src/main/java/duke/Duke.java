package duke;
import java.io.IOException;

import duke.command.Command;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * The main class for the Duke program.
 * It manages the initialization, execution, and termination of the program.
 */
public class Duke {

    /** Handles reading and storing tasks. */
    private Storage storage;
    /** List of tasks managed by Duke. */
    private TaskList tasks;

    /** User interface for interactions. */
    private final Ui ui;


    /**
     * Constructs a new Duke instance with the specified file path for storage.
     */
    public Duke() {
        ui = new Ui();
        assert ui != null : "UI should be properly initialized!";

        try {
            storage = new Storage("./data/duke.txt");
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException ie) {
            ui.showError("An error occurred while initializing the storage: " + ie.getMessage());
            System.exit(0);
        } catch (DukeException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        assert tasks != null : "Tasks should be properly initialized!";
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (IOException ie) {
            return ui.showError(ie.getMessage());
        } catch (DukeException de) {
            return ui.showError(de.getMessage());
        } finally {
            ui.showLine();
        }
    }

}
