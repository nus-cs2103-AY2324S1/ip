package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * Represents a ExitCommand class that deals with the command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Displays the bye message.
     *
     * @param taskList Task list of tasks.
     * @param storage  Storage.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        return GobbleMessage.getGobbleDialog("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return True as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
