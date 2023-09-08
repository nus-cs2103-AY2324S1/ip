package aichan.command;

import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;

/**
 * Represents a command to find task with keyword.
 * Inherits from the Command class.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand object.
     * Initializes the keyword.
     *
     * @param keyword Word to find in task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays the tasks have the keyword.
     *
     * @param tasks A list of tasks.
     * @param ui User interface to display message.
     * @param storage Storage for storing and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the matching tasks in your list:\n";
        int size = tasks.getSize();
        int matchingTaskNumber = 0;
        for (int i = 1; i <= size; i++) {
            Task task = tasks.getTask(i);
            if (task.hasKeyword(this.keyword)) {
                response = response + i + "." + task.toString() + "\n";
                matchingTaskNumber++;
            }
        }
        if (matchingTaskNumber == 0) {
            response = response + "------None task matches the keyword------";
        }
        return response;
    }
}
