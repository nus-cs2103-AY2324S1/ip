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
     * @param taskList list of tasks.
     * @param storage  storage.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        return GobbleMessage.getDukeDialog("Bye. Hope to see you again soon!", "Exit");
    }

    /**
     * Returns true as this is an exit command.
     *
     * @return true as this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
