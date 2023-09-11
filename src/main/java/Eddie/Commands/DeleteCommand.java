package Eddie.Commands;

import Eddie.GUI.Ui;
import Eddie.Storage;
import Eddie.TaskList;
import Eddie.Tasks.Task;

public class DeleteCommand {
    public static String execute(int num) {
        Task t = TaskList.get(num - 1);
        TaskList.delete(num - 1);


        Storage.write();
        return Ui.removeTask(t.toString(), TaskList.size());
    }
}
