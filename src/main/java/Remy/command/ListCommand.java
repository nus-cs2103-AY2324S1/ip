package Remy.command;
import Remy.Task.TaskList;
import Remy.Ui;
import Remy.Storage;

/**
 * A Command that prints a String representation of the current TaskList upon executing.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints String representation of the current TaskList.
     * @param taskList The TaskList to be acted on.
     * @param ui Handles User interaction.
     * @param storage Handles saving the updated TaskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printList(taskList);
    }
}
