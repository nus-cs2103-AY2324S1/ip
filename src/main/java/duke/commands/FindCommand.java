package duke.commands;

import duke.TaskList;

/**
 * Finds items from application state.
 */
public class FindCommand implements Command {
    /**
     * Searches for tasks from the task list.
     *
     * @param input    The user input.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        String[] args = input.split(" ", 2);
        return taskList.searchTasks(args[1]);
    }
}
