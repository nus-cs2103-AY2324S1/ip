package duke.commands;

import duke.Messages;
import duke.TaskList;
import javafx.application.Platform;

/**
 * Terminates the program.
 */
public class ExitCommand implements Command {
    /**
     * Prints an exit message and terminates the program.
     *
     * @param input    The user input.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        Platform.exit();
        return Messages.EXIT_MESSAGE;
    }
}
