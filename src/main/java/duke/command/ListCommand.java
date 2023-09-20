package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.GobbleMessage;

/**
 * R
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks.
     *
     * @param taskList TaskList list of tasks
     * @param storage  Storage.
     */
    @Override
    public GobbleMessage execute(TaskList taskList, Storage storage) {
        return GobbleMessage.getGobbleDialog(taskList.toString());
    }
}
