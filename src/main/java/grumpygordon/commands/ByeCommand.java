package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    private static final String OUTRO = "Bye. Hope to never see you again.";

    /**
     * Executes the bye command.
     * @param tasks The list of tasks
     * @param ui The ui of the program
     * @param storage The storage of the program
     * @return The outro string
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return OUTRO;
    }
}
