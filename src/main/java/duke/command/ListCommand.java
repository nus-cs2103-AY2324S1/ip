package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to list all the tasks saved.
 */
public class ListCommand extends Command {
    /**
     * List all the tasks saved.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.listTaskResponse(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    /**
     * Checks if command will end program.
     * @return False.
     */
    public boolean isExit() {
        return false;
    };
}
