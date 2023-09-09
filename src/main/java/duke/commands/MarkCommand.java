package duke.commands;

import java.io.IOException;

import duke.Messages;
import duke.TaskList;

/**
 * Marks a task.
 */
public class MarkCommand implements Command {
    /**
     * Sets a given task to be marked.
     *
     * @param input    The user input of the task to be marked.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        try {
            String taskString = taskList.markTask(index);
            return String.format(Messages.MARKED_MESSAGE, taskString);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
