package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.Task;
import Jeoe.Tasks.TaskManager;
import java.util.ArrayList;

public class ListCommand extends Command {
    ListCommand (String input) {
        super(false);
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = taskManager.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = String.valueOf(i + 1) + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        ui.displayReply(reply);
    }
}
