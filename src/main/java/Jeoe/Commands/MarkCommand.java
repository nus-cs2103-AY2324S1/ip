package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;

public class MarkCommand extends Command {
    int idxMark;

    MarkCommand (String input) {
        super(false);
        idxMark = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // add to the storage in Task & save into HDD
        taskManager.mark(idxMark);
        storageManager.save(taskManager.getTasks());

        // format the reply
        String reply = "Nice! I've marked this task as done:\n" + taskManager.getTask(idxMark).toString() + "\n";

        // add to the reply
        ui.displayReply(reply);
    }
}
