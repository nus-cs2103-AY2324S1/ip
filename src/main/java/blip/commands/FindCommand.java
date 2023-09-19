package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.storage.BlipStorage;
/**
 * Represents find command to find tasks that match description.
 */
public class FindCommand extends Command {
    /**
     * Description of task to find.
     */
    String description;

    /**
     * Creates an instance of FindCommand.
     *
     * @param description The description of task to find.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the finding of matching tasks in task list.
     *
     * @param tasks The Array List of tasks to find matching description
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList tasks, BlipUI ui, BlipStorage storage) {
        return ui.listTasksMatched(tasks, this.description);
    }



}
