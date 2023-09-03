package Jeoe.Commands;

import java.util.ArrayList;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.Task;
import Jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class ListCommand.
 * It is meant to display a list view of all the tasks currently stored.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class ListCommand extends Command {

    /**
     * Constructor for a ListCommand object.
     * @param input The string input by the user to parse into a command.
     */
    ListCommand(String input) {
        super(false);
    }

    /**
     * Executes the list command.
     * Iterates through the list of task then displays a list view of the tasks to the user.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
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
