package commands;

import tasks.Task;
import tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks) {
        ArrayList<Task> filteredTaskList = tasks.findByKeyword(keyword);

        System.out.println("Here are the matching tasks in your list:");
        for (Task task: filteredTaskList) {
            System.out.println(task.toString());
        }
    }
}
