package remy.command;

import remy.Storage;
import remy.Ui;
import remy.task.TaskList;

/**
 * A Command that prints a String representation of the current TaskList upon executing.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Prints String representation of the current TaskList.
     *
     * @param taskList The TaskList to be acted on.
     * @param ui       Handles User interaction.
     * @param storage  Handles saving the updated TaskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.toString();
    }
}
