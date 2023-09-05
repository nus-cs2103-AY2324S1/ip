package dukeapp.commands;

import dukeapp.Messages;
import dukeapp.TaskList;
import dukeapp.Ui;

import java.io.IOException;

/**
 * Marks a task.
 */
public class MarkCommand implements Command {
    /**
     * Sets a given task to be marked.
     *
     * @param input    The user input of the task to be marked.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        try {
            String taskString = taskList.markTask(index);
            ui.displayMessage(String.format(Messages.MARKED_MESSAGE, taskString));
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }
    }
}
