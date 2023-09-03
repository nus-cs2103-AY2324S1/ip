package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.Task;
import Jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class DeleteCommand.
 * It is meant to execute the deletion of a task.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class DeleteCommand extends Command {

    /** Index in task list to delete task. */
    private int idxDel;

    /**
     * Constructor for a DeleteCommand object.
     * @param input The string input by the user to parse into a command.
     */
    DeleteCommand(String input) {
        super(false);
        idxDel = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the delete command.
     * Deletes a task from the list, remove it from local storage then prints out delete confirmation.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // add to the storage in Task & save into HDD
        Task t = taskManager.deleteIndex(idxDel);
        storageManager.save(taskManager.getTasks());

        // format the reply
        String reply = "Noted. I've removed this task:\n" + t.toString() + "\n"
                + "Now you have " + taskManager.getTasksSize() + " tasks in the list.\n";

        // add to the reply
        ui.displayReply(reply);
    }
}
