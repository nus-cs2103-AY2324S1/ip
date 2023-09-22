package commands;

import tasks.TaskList;

/**
 * This class instructs the application to list all current tasks in the task list.
 */
public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(TaskList tasks) {
        return tasks.listTasks();
    }
}
