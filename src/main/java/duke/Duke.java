package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.StorageCreationException;
import duke.exceptions.UnknownCommandException;

/**
 * Serves as the entry point to run the application.
 */
public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Parser app = new Parser();

        // Load tasks from storage
        try {
            Storage storage = new DukeStorage();
            taskList.setStorage(storage);
            taskList.loadTasks();
        } catch (IOException | InsufficientArgumentsException
                 | DateTimeParseException | StorageCreationException e) {
            ui.displayError(e.getMessage());
        }

        // Greet the user
        ui.displayMessage(Messages.GREETING_MESSAGE, true, true);

        // Read user input until program exits
        while (true) {
            String input = ui.getInput();
            try {
                app.executeCommand(input, taskList, ui);
            } catch (UnknownCommandException e) {
                ui.displayError(e.getMessage());
            }
        }
    }
}
