package Eddie.Commands;

import Eddie.GUI.Ui;
import Eddie.TaskList;
import Eddie.Tasks.Task;

/**
 * Command to display an indexed list of tasks currently in the list.
 */
public class ListCommand {
    public static String execute() {
        int listSize = TaskList.size();
        String s = "";
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = TaskList.get(i);
            s = s + Ui.listTask(num, taskToList.toString()) + "\n";
        }

        return s;
    }
}
