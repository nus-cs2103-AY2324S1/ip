package jarvis.commands;

import java.util.ArrayList;

import jarvis.gui.Ui;
import jarvis.storage.Storage;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            return ui.printResponse("Congratulations Master!\n" + "There is no task at the moment!");
        } else {
            storage.loadTasks();
            ArrayList<Task> tasks = taskList.getTaskList();
            return ui.printTasks(tasks);
        }
    }
}
