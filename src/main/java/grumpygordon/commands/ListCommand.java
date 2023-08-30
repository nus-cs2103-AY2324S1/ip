package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param ui The user interface
     * @param storage The storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandMessage(tasks.toString());
    }
}
