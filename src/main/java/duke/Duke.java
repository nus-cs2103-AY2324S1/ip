package duke;

import command.Command;
import task.TaskList;

import java.time.format.DateTimeParseException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.readFile());
    }

    /**
     * Runs the program until termination
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (NumberFormatException e) { // user inputs invalid argument for mark and unmark eg. "mark ab"
                this.ui.showError("Invalid command! Please enter only one valid task ID (numbers only)");
            } catch (DateTimeParseException e) {
                this.ui.showError("Invalid date and time format! Please use the format dd/mm/yyyy hhmm");
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            } finally {
                this.ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}

