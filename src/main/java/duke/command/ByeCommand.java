package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts program to end.
 */
public class ByeCommand extends Command {
    /**
     * Prompts program to end.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitResponse();
    };
    /**
     * Checks if command will end program.
     * @return True.
     */
    public boolean isExit() {
        return true;
    };
}
