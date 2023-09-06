package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the programme to list all the tasks saved.
 */
public class ListCommand extends Command {
    /**
     * List all the tasks saved.
     * @param tasks List of task stored by the programme.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTaskResponse(tasks);
    };
    /**
     * Checks if command will end programme.
     * @return False.
     */
    public boolean isExit() {
        return false;
    };
}
