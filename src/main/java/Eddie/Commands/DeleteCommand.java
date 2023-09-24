package Eddie.Commands;

import Eddie.GUI.Ui;
import Eddie.Storage;
import Eddie.TaskList;
import Eddie.Tasks.Task;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand {
    public static String execute(int num) {
        Task t = TaskList.get(num);
        TaskList.delete(num);


        Storage.write();
        return Ui.removeTask(t.toString(), TaskList.size());
    }
}
