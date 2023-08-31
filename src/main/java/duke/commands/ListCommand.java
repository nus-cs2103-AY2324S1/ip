package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Task;

import java.util.List;

/**
 * Represents a command to print the contents of the task list
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printToScreen("Here are your tasks:");
        List<Task> tasks = taskList.getTasks();
        int taskIdx = 0;
        for (Task t : tasks) {
            System.out.printf("%d.%s\n", ++taskIdx, t);
        }
    }
}
