package commands;

import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;

/**
 * This class instructs the application to find all tasks that match the keyword.
 */
public class FindCommand implements Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        ArrayList<Task> filteredTaskList = tasks.findByKeyword(keyword);

        String message = "";
        message += "Here are the matching tasks in your list:\n";
        for (Task task: filteredTaskList) {
            message += task.toString() + "\n";
        }

        return message;
    }
}
