package buddy.commands;

import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for marking a task in the task list as not done.
 */
public class MarkAsUndoneCommand extends Command {
    private int index;

    /**
     * The constructor for a MarkAsUndoneCommand.
     *
     * @param index The zero-based index of the task.
     */
    public MarkAsUndoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        tasks.markAsNotDone(index);
        String response = ui.printMessage("OK, I've marked this task as not done yet:\n"
                + tasks.getTask(index).toString());
        storage.writeToFile(tasks.getAllTasks());
        return response;
    }
}
