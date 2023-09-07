package duke;

import java.time.format.DateTimeParseException;

import command.Command;
import task.TaskList;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initialises a Duke chatbot
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/tasks.txt");
        this.tasks = new TaskList(storage.readFile());
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (NumberFormatException e) {
            // user input has invalid argument for mark and unmark eg. "mark ab"
            return "Invalid command! Please enter only one valid task ID (numbers only)";
        } catch (DateTimeParseException e) {
            // user input has date/time in invalid format
            return "Invalid date and time format! Please use the format dd/mm/yyyy hhmm";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

