package duke.commands;

import duke.Messages;
import duke.TaskList;
import duke.Ui;

/**
 * Terminates the program.
 */
public class ExitCommand implements Command {
    /**
     * Prints an exit message and terminates the program.
     *
     * @param input    The user input.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        ui.displayMessage(Messages.EXIT_MESSAGE);
        System.exit(0);
    }
}
