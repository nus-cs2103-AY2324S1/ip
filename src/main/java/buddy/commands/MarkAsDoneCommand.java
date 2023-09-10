package buddy.commands;

import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for marking a task in the task list as done.
 */
public class MarkAsDoneCommand extends Command {
    private int index;

    /**
     * The constructor for a MarkAsDoneCommand.
     *
     * @param index The zero-based index of the task.
     */
    public MarkAsDoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        tasks.markAsDone(index);
        ui.printMessage("NICE! I've marked this task as done:\n" + tasks.getTask(index).toString());
        storage.writeToFile(tasks.getAllTasks());
    }
}
