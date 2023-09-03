package Jeoe.Commands;

import java.util.ArrayList;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.Task;
import Jeoe.Tasks.TaskManager;


/**
 * This class encapsulates the class FindCommand.
 * It is meant to help the user find a task in the list of tasks,
 * with description matching the user input.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class FindCommand extends Command {

    /** Contains the filter words that will filter our matching tasks. */
    private String filterWords;

    /**
     * Constructor for a FindCommand object.
     * @param input The string input by the user to parse into a command.
     */
    FindCommand(String input) {
        super(false);
        this.filterWords = input.replaceFirst("find ", "");
    }

    /**
     * Executes the find command.
     * Goes through the list and finds the tasks with description matching the user input.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        String reply = "Here are the matching tasks in your list:\n";
        ArrayList<Task> tasks = taskManager.getTasks();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            // check if the task has matching words, else continue
            if (task.getDescription().contains(this.filterWords)) {
                String num = String.valueOf(i + 1) + ". ";
                reply += num + task + "\n";
            }
        }

        ui.displayReply(reply);
    }
}
