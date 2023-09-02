package haste.commands;

import haste.data.TaskList;
import haste.tasks.Task;
import haste.ui.Ui;

public class MarkCommand extends Command{
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui) {
        Task markedTask = tasks.getTask(index);
        markedTask.markDone();
        String taskDesc = markedTask.toString();
        ui.mark(taskDesc, tasks);
    }
}
