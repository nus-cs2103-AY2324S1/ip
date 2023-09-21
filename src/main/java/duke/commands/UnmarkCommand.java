package duke.commands;

import java.io.IOException;

import duke.Messages;
import duke.TaskList;

/**
 * Unmarks a task.
 */
public class UnmarkCommand implements Command {
    /**
     * Sets a given task to be unmarked.
     *
     * @param input    The user input of the task to be unmarked.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        try {
            String taskString = taskList.unmarkTask(index);
            return String.format(Messages.UNMARKED_MESSAGE, taskString);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
