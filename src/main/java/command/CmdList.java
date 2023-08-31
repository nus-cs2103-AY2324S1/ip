package command;

import task.Task;
import task.TaskList;
import ui.Ui;

public class CmdList extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            Task task = taskList.getTask(i - 1);
            ui.print(String.format("%d. %s", i, task.toString()));
        }
    }

}
