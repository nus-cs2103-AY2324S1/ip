package jeoe.Commands;

import jeoe.Exceptions.IndexOutOfBoundsException;
import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class UnmarkCommand.
 * It is meant to un-mark a task as done in the list of tasks.
 *
 * @author Joe Chua
 * @version Week-6
 */
public class UnmarkCommand extends Command {

    /** Index in the task list to un-mark a task. */
    private int idxUnmark;

    /** validity of command. */
    private boolean isValid = true;

    /**
     * Constructor for a UnmarkCommand object.
     * @param input The string input by the user to parse into a command.
     */
    UnmarkCommand(String input) {
        super(false);
        try {
            idxUnmark = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            isValid = false;
        }
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
        try {
            if (!isValid) {
                throw new Exception("unmark command is invalid, please try again");
            }
            // add to the storage in Task & save into HDD
            taskManager.unmark(idxUnmark);
            storageManager.save(taskManager.getTasks());

            // format the reply
            String reply = "OK, I've marked this task as not done yet:\n"
                    + taskManager.getTask(idxUnmark).toString() + "\n";

            // add to the reply
            ui.displayReply(reply);
        } catch (IndexOutOfBoundsException e) {
            ui.displayReply(e.getMessage());
        } catch (Exception e) {
            ui.displayReply(e.getMessage());
        }
    }

    /**
     * Executes the unmark command.
     * Un-marks a task from the list, updates the local storage then returns the un-marked confirmation string.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public String executeAndReply(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        try {
            if (!isValid) {
                throw new Exception("unmark command is invalid, please try again");
            }
            // add to the storage in Task & save into HDD
            taskManager.unmark(idxUnmark);
            storageManager.save(taskManager.getTasks());

            // format the reply
            String reply = "OK, I've marked this task as not done yet:\n"
                    + taskManager.getTask(idxUnmark).toString() + "\n";

            // add to the reply
            return ui.getReply(reply);
        } catch (IndexOutOfBoundsException e) {
            return ui.getReply(e.getMessage());
        } catch (Exception e) {
            return ui.getReply(e.getMessage());
        }

    }
}
