package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Marks a task in the list as incomplete.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Creates an UnmarkCommand that marks a task in the list as incomplete.
     * @param input The user input indicating which task to mark as incomplete.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.setTaskIncomplete(this.input);
    }
}
