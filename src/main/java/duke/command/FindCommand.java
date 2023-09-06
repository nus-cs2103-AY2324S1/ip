package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to find tasks within the task list.
 */
public class FindCommand extends Command {

    /** The String to search for. */
    private String findStr;

    /**
     * Creates a FindCommand with a specified search string.
     *
     * @param findStr The search string to search the TaskList for.
     */
    public FindCommand(String findStr) {
        this.findStr = findStr;
    }

    /**
     * Finds tasks in the TaskList with the given search string, and prints the matching tasks. Prints a message if no
     * matching tasks are found.
     *
     * @param tasks The task list to search using the search keyword.=
     * @param storage The storage to save and update tasks (if needed).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList foundTasks = tasks.find(findStr);
        StringBuilder responseBuilder = new StringBuilder();
        if (foundTasks.getSize() > 0) {
            responseBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.getSize(); i++) {
                responseBuilder.append(i + 1).append(". ").append(foundTasks.getTaskString(i + 1)).append("\n");
            }
        } else {
            responseBuilder.append("There are no tasks with the given keyword!");
        }
        return responseBuilder.toString();
    }

    /**
     * Gets the command type for the FindCommand.
     *
     * @return Find.
     */
    @Override
    public String getCommandType() {
        return "Find";
    }
}
