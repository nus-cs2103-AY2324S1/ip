package duke;

import duke.commands.Command;
import duke.*;

import java.time.format.DateTimeParseException;

/**
 * Main class of the application.
 */
public class Duke {

    private static TaskList tasks;
    private Ui ui;
    private DataStorage store;

    /**
     * Initialises the Duke application.
     */
    public Duke() {
        ui = new Ui();
        store = new DataStorage();
        store.loadTasks();
        tasks = new TaskList(store);

    }

    /**
     * Reads and executes commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, store);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseError();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs application.
     *
     * @param args (not used)
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
