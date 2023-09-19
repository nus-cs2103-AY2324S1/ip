package jeoe.Commands;

import java.util.ArrayList;

import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.Task;
import jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class SortCommand.
 * It is meant to sort the current list of tasks.
 *
 * @author Joe Chua
 * @version Week-6
 */
public class SortCommand extends Command {

    /**
     * Constructor for a SortCommand object.
     * @param input The string input by the user to parse into a command.
     */
    SortCommand(String input) {
        super(false);
    }

    /**
     * Executes the Sort Command.
     * Sorts the list then saves that new sorted list.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // sort the things in the list then save the new list
        taskManager.sort();
        storageManager.save(taskManager.getTasks());

        // add to the reply
        String reply = "Here are the sorted tasks in your list:\n";
        ArrayList<Task> tasks = taskManager.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = String.valueOf(i + 1) + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }
        ui.displayReply(reply);
    }

    /**
     * Executes the Sort Command.
     * Sorts the list then saves that new sorted list.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public String executeAndReply(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        // sort the things in the list then save the new list
        taskManager.sort();
        storageManager.save(taskManager.getTasks());

        // add to the reply
        String reply = "Here are the sorted tasks in your list:\n";
        ArrayList<Task> tasks = taskManager.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = String.valueOf(i + 1) + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        // add to the reply
        return ui.getReply(reply);
    }
}
