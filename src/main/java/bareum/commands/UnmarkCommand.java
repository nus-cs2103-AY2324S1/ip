package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.markAsUndone(index);
        storage.saveAllTasks(taskList);
        Ui.reply("Okay, I've marked this task as not done yet:\n" + taskList.get(index).toString());
    }
}
