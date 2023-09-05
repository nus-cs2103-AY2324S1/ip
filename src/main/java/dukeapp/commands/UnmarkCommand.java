package dukeapp.commands;

import dukeapp.Messages;
import dukeapp.TaskList;
import dukeapp.Ui;

import java.io.IOException;

/**
 * Unmarks a task.
 */
public class UnmarkCommand implements Command {
    /**
     * Sets a given task to be unmarked.
     *
     * @param input    The user input of the task to be unmarked.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        try {
            String taskString = taskList.unmarkTask(index);
            ui.displayMessage(String.format(Messages.UNMARKED_MESSAGE, taskString));
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }
    }
}
