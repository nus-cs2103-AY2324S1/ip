package Commands;

import Others.StorageManager;
import Others.Ui;
import Tasks.TaskManager;

public class UnmarkCommand extends Command {
    int idxUnmark;

    UnmarkCommand (String input) {
        super(false);
        idxUnmark = Integer.parseInt(input.split(" ")[1]) - 1;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // add to the storage in Task & save into HDD
        taskManager.unmark(idxUnmark);
        storageManager.save(taskManager.getTasks());

        // format the reply
        String reply = "OK, I've marked this task as not done yet:\n" + taskManager.getTask(idxUnmark).toString() + "\n";

        // add to the reply
        ui.displayReply(reply);
    }
}
