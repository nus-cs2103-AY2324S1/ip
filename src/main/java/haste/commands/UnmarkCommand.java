package haste.commands;

import haste.data.TaskList;
import haste.tasks.Task;
import haste.ui.Ui;

public class UnmarkCommand extends Command{
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    public void execute(TaskList tasks, Ui ui) {
        Task unmarkedTask = tasks.getTask(index);
        unmarkedTask.markUndone();
        String taskDesc = unmarkedTask.toString();
        ui.mark(taskDesc, tasks);
    }
}
