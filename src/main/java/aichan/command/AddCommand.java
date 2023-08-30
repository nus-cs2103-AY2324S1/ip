package aichan.command;

import aichan.TaskList;
import aichan.Ui;
import aichan.task.Deadline;
import aichan.task.Event;
import aichan.task.Task;
import aichan.task.ToDo;

public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    public void execute(TaskList tasks, Ui ui) {
        // control the main logic
        // add/delete task, show words
        tasks.addTask(task);
        int size = tasks.getSize();
        ui.showMessage(String.format("Got it. I've added this task:\n  %s\n" +
                       "Now you have %d tasks in the list", task, size));
    }
    @Override
    public boolean isExit(){
        return false;
    }
}
