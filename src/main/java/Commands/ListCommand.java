package Commands;

import Others.StorageManager;
import Others.Ui;
import Tasks.Task;
import Tasks.TaskManager;
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
