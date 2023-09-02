package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command.
     * @param tasks The list of tasks
     * @param ui The ui of the program
     * @param storage The storage of the program
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showOutroMessage();
    }
}
