package duke.main;

import java.io.File;

import duke.Parser;
import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

/**
 * Main class of the Duke program.
 */
public class Duke {
    private static final String TASK_FILEPATH = "." + File.separator + "data" + File.separator + "tasks.txt";
    private static final Parser parser = new Parser();
    private static TaskListStorage taskListStorage;

    public Duke() {
        taskListStorage = new TaskListStorage(TASK_FILEPATH);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     *
     * param args The command line arguments, for now it serves no purpose.
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
