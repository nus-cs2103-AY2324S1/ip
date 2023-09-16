package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Deletes a task in the list.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Creates a DeleteCommand that will delete a task based on the specified input.
     * @param input The user input indicating which task to be deleted.
     */
    public DeleteCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.deleteTask(this.input);
    }
}
