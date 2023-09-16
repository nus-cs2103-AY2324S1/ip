package commands;

import functions.TaskList;

/**
 * The class for executing a list command to list out all tasks in the task list
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Constructs a new ListCommand object with the specified task list.
     *
     * @param taskList The task list to list all tasks from.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        String message = "";

        boolean taskListIsEmpty = taskList.size() == 0;
        if (taskListIsEmpty) {
            return "There are no tasks stored currently!";
        }

        for (int i = 0; i < taskList.size(); i++) {
            message += String.format("%d. %s", i + 1, taskList.get(i).getTaskAsString());
            message += "\n";
        };
        return message;
    }
}
