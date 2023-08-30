package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ExitCommand class represents a command to exit the Duke application.
 *
 * @author selwyn
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand by changing the exit status and displaying an exit message.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object handling user interface interactions.
     * @param storage The Storage object handling storage-related operations.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.changeExitStatus();
        ui.printExit();
    }
}