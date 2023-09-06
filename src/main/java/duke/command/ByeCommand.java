package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts programme to end.
 */
public class ByeCommand extends Command {
    /**
     * Prompts programme to end.
     * @param tasks List of task stored by the programme.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitResponse();
    };
    /**
     * Checks if command will end programme.
     * @return True.
     */
    public boolean isExit() {
        return true;
    };
}
