package duke;

import java.time.format.DateTimeParseException;

import duke.commands.Command;



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
    public String run() {
        return ui.showWelcome();
    }

    /**
     * Fetches the appropriate text response to user's command.
     * @param input User's string command.
     * @return Appropriate text response to user's command.
     */
    public String getResponse(String input) {
        Command c = null;
        try {
            c = Parser.parse(input, tasks);
            return c.execute(tasks, ui, store);
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (DateTimeParseException e) {
            return ui.showDateTimeParseError();
        }
    }
}


