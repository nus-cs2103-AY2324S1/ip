package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.Task;
import Jeoe.Tasks.TaskManager;

public class DeleteCommand extends Command {
    int idxDel;
    DeleteCommand (String input) {
        super(false);
        idxDel = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {

        // add to the storage in Task & save into HDD
        Task t = taskManager.deleteIndex(idxDel);
        storageManager.save(taskManager.getTasks());

        // format the reply
        String reply = "Noted. I've removed this task:\n" +
                t.toString() + "\n" +
                "Now you have " + taskManager.getTasksSize() + " tasks in the list.\n";

        // add to the reply
        ui.displayReply(reply);
    }
}
