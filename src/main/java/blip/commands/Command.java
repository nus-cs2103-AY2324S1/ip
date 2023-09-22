package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.storage.BlipStorage;
/**
 * Represents a Command for Blip.
 */
public abstract class Command {
    /**
     * Executes the command for Blip
     *
     * @param taskList The Array List of tasks to do commands on
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    public abstract String execute(TaskList taskList, BlipUI ui, BlipStorage storage);


}
