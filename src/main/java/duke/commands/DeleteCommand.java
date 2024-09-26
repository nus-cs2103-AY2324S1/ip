package duke.commands;

import java.io.IOException;

import duke.Messages;
import duke.TaskList;

/**
 * Deletes a task.
 */
public class DeleteCommand implements Command {
    /**
     * Deletes a task from the application's task list.
     *
     * @param input    The user input of the task to be deleted.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        try {
            int index = Integer.parseInt(indexString) - 1;
            String taskString = taskList.deleteTask(index);
            return String.format(Messages.DELETE_MESSAGE, taskString, taskList.getTaskCount());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return String.format(Messages.ERROR_PREFIX,
                    String.format(Messages.INVALID_INDEX_ERROR_MESSAGE, indexString));
        } catch (IOException e) {
            return String.format(Messages.ERROR_PREFIX, e.getMessage());
        }
    }
}
