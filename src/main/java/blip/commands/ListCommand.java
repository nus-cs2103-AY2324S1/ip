package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

/**
 * Represents the list command to list out all the tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command to list out all the tasks.
     *
     * @param taskList The Array List of tasks to list out tasks from
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        return ui.listsTasksMsg(taskList);
    }
}
