package jeoe.Commands;

import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class UnmarkCommand.
 * It is meant to un-mark a task as done in the list of tasks.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class UnmarkCommand extends Command {

    /** Index in the task list to un-mark a task. */
    private int idxUnmark;

    /**
     * Constructor for a UnmarkCommand object.
     * @param input The string input by the user to parse into a command.
     */
    UnmarkCommand(String input) {
        super(false);
        idxUnmark = Integer.parseInt(input.split(" ")[1]) - 1;
    }

    /**
     * Executes the unmark command.
     * Un-marks a task from the list, updates the local storage then prints out the un-marked confirmation.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // add to the storage in Task & save into HDD
        taskManager.unmark(idxUnmark);
        storageManager.save(taskManager.getTasks());

        // format the reply
        String reply = "OK, I've marked this task as not done yet:\n"
                + taskManager.getTask(idxUnmark).toString() + "\n";

        // add to the reply
        ui.displayReply(reply);
    }

    /**
     * Executes the unmark command.
     * Un-marks a task from the list, updates the local storage then returns the un-marked confirmation string.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public String executeAndReply(TaskManager taskManager, Ui ui, StorageManager storageManager)  {
        // add to the storage in Task & save into HDD
        taskManager.unmark(idxUnmark);
        storageManager.save(taskManager.getTasks());

        // format the reply
        String reply = "OK, I've marked this task as not done yet:\n"
                + taskManager.getTask(idxUnmark).toString() + "\n";

        // add to the reply
        return ui.getReply(reply);
    }
}
