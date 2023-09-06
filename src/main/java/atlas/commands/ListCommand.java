package atlas.commands;

import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.components.Ui;
import atlas.tasks.Task;


/**
 * Command to print the contents of the task list
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printToScreen("Here are your tasks:");
        List<Task> tasks = taskList.getTasks();
        int taskIdx = 0;
        for (Task t : tasks) {
            ui.printToScreen(String.format("%d. %s", ++taskIdx, t));
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        StringBuilder output = new StringBuilder("Here are your tasks:\n");
        int taskIdx = 0;
        for (Task t : tasks) {
            output.append(String.format("%d. %s\n", ++taskIdx, t));
        }
        return output.toString();
    }
}
