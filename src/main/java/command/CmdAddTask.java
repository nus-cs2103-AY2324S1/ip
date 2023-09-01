package command;

import task.Task;
import task.TaskList;
import ui.Ui;

public class CmdAddTask extends Command {
    private Task task;

    public CmdAddTask(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task);
        ui.print("Got it. I've added this task:");
        ui.print(task.toString());
        ui.print(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

}
