package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents a command that the user has inputted.
 */
public class ListTasksCommand extends Command {

    /**
     * Constructs a new ListTasksCommand object.
     *
     * @param taskList The current list of tasks.
     * @param args     The arguments supplied by the user.
     */
    public ListTasksCommand(TaskList taskList, String args) {
        super(CommandType.LIST_TASKS, taskList, args);
    }

    /**
     * Lists all the tasks in the list of tasks.
     *
     * @return The response to the user.
     */
    @Override
    public String execute() {
        return this.taskList.listTasks();
    }
}
