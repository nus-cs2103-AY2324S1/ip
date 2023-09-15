package Eddie.Commands;

import Eddie.GUI.Ui;
import Eddie.Storage;
import Eddie.TaskList;
import Eddie.Tasks.Task;

public class AddCommand {
    public static String execute(Task t){
        String taskName = t.getName();
        TaskList.add(t);


        Storage.write();
        return Ui.addTask(t.toString(), TaskList.size());
    }
}
