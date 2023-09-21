package duke.main;

import java.io.File;

import duke.Parser;
import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.reminder.ReminderManager;

/**
 * Main class of the Duke program.
 */
public class Duke {
    private static final String TASK_FILEPATH = "." + File.separator + "data" + File.separator + "tasks.txt";
    private static final Parser parser = new Parser();
    private static TaskListStorage taskListStorage;
    private static ReminderManager reminderManager;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        Object mutex = new Object();

        taskListStorage = new TaskListStorage(TASK_FILEPATH, mutex);
        reminderManager = new ReminderManager(taskListStorage.getTaskQueue(), mutex);
        reminderManager.start();
    }

    /**
     * Returns the response of the program to the user input.
     * @param input The user input.
     * @return The response of the program to the user input.
     */
    public String getResponse(String input) {
        try {
            return parser.dispatch(input).execute(taskListStorage);
        } catch (UnknownCommandException | MissingDescriptionException | IncorrectCommandFormatException
                | InvalidIndexException | InvalidTimeFormatException e) {
            return e.getMessage();
        }
    }
}
