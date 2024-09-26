package jeoe.Commands;

import jeoe.Exceptions.IndexOutOfBoundsException;
import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class MarkCommand.
 * It is meant to mark a task as done in the list of tasks.
 *
 * @author Joe Chua
 * @version Week-6
 */
public class MarkCommand extends Command {

    /** Index in the task list to mark a task. */
    private int idxMark;

    /** validity of command. */
    private boolean isValid = true;
    /**
     * Constructor for a MarkCommand object.
     * @param input The string input by the user to parse into a command.
     */
    MarkCommand(String input) {
        super(false);
        try {
            idxMark = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            // if the delete command is not valid
            isValid = false;
        }
    }

    /**
     * Executes the mark command.
     * Marks a task from the list, updates the local storage then prints out the marked confirmation.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        try {
            if (!isValid) {
                throw new Exception("mark command is invalid, please try again");
            }
            // add to the storage in Task & save into HDD
            taskManager.mark(idxMark);
            storageManager.save(taskManager.getTasks());

            // format the reply
            String reply = "Nice! I've marked this task as done:\n" + taskManager.getTask(idxMark).toString() + "\n";

            // add to the reply
            ui.displayReply(reply);
        } catch (IndexOutOfBoundsException e) {
            ui.displayReply(e.getMessage());
        } catch (Exception e) {
            ui.displayReply(e.getMessage());
        }
    }

    /**
     * Executes the mark command.
     * Marks a task from the list, updates the local storage then returns out the marked confirmation string.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public String executeAndReply(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        try {
            if (!isValid) {
                throw new Exception("mark command is invalid, please try again");
            }
            // add to the storage in Task & save into HDD
            taskManager.mark(idxMark);
            storageManager.save(taskManager.getTasks());

            // format the reply
            String reply = "Nice! I've marked this task as done:\n" + taskManager.getTask(idxMark).toString() + "\n";

            // add to the reply
            return ui.getReply(reply);
        } catch (IndexOutOfBoundsException e) {
            return ui.getReply(e.getMessage());
        } catch (Exception e) {
            return ui.getReply(e.getMessage());
        }
    }
}
