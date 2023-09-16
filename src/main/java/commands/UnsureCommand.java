package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to display an unknown command error.
 */
public class UnsureCommand extends Command {

    /**
     * Initializes a new UnsureCommand instance.
     */
    public UnsureCommand() {
        super(false);
    }

    /**
     * Executes the unsure command, returning an error message.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return A message indicating that the input was unknown.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
