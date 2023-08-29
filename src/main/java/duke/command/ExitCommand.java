package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Command use to end the program.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by sending a farewell message.
     *
     * @param tasks The task list, not used in the exit command.
     * @param ui    The user interface used to send the farewell message.
     * @return {@code true} as the program should terminate.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        ui.respond("Bye. Hope to see you again soon!");
        return true;
    }
}
