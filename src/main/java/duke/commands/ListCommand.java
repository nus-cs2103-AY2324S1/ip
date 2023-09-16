package duke.commands;

import duke.TaskList;

/**
 * Lists items from the application's task list.
 */
public class ListCommand implements Command {
    /**
     * Lists all the items in the application's task list.
     *
     * @param input    The user input.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        return taskList.listTasks();
    }
}

