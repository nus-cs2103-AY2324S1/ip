package jarvis.commands;

import java.util.ArrayList;

import jarvis.Storage;
import jarvis.Task;
import jarvis.TaskList;
import jarvis.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.printResponse("Congratulations Master! There is no task at the moment!");
        } else {
            ArrayList<Task> tasks = taskList.getTaskList();
            ui.printTasks(tasks);
        }
    }
}
