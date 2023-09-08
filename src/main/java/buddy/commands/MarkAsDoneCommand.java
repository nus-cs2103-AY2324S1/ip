package buddy.commands;

import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class MarkAsDoneCommand extends Command {
    private int index;

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
