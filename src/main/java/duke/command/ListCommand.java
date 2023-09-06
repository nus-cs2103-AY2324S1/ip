package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to list all tasks within the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the given ListCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to be printed.
     * @param storage The storage to save and update tasks (if needed).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder responseBuilder = new StringBuilder();

        responseBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            responseBuilder.append((i + 1) + ". " + tasks.getTaskString(i + 1) + "\n");
        }

        return responseBuilder.toString();
    }

    /**
     * Gets the command type for the ListCommand.
     *
     * @return List.
     */
    @Override
    public String getCommandType() {
        return "List";
    }
}
