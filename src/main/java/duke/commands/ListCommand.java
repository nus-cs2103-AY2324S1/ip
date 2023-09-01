package duke.commands;

import java.util.ArrayList;
import java.util.List;

import duke.TaskList;

/**
 * A command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public CommandResult run(TaskList tasks) {
        if (tasks.isEmpty()) {
            return new CommandResult("You have no tasks in your list!");
        }

        List<String> response = new ArrayList<>();

        response.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskEntry = String.format("%d. %s", i + 1, tasks.get(i));
            response.add(taskEntry);
        }
        response.add(String.format("You have %d %s in your list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));

        return new CommandResult(response);
    }
}
