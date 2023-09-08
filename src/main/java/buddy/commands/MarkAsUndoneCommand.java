package buddy.commands;

import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class MarkAsUndoneCommand extends Command {
    private int index;

    public MarkAsUndoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        tasks.markAsNotDone(index);
        ui.printMessage("OK, I've marked this task as not done yet:\n" + tasks.getTask(index).toString());
        storage.writeToFile(tasks.getAllTasks());
    }
}
