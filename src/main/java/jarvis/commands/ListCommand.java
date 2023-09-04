package jarvis.commands;

import java.util.ArrayList;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

/**
 * Represents a command to list the tasks currently present in the Jarvis app.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command, which lists the tasks currently present in the
     * Jarvis app.
     *
     * @param taskList The TaskList containing the tasks to be listed.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     */
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
