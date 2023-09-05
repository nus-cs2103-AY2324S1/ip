package dukeapp.commands;

import dukeapp.Messages;
import dukeapp.TaskList;
import dukeapp.Ui;

import java.io.IOException;

/**
 * Deletes a task.
 */
public class DeleteCommand implements Command {
    /**
     * Deletes a task from the application.
     *
     * @param input    The user input of the task to be deleted.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        try {
            String taskString = taskList.deleteTask(index);
            ui.displayMessage(String.format(Messages.DELETE_MESSAGE, taskString,
                    taskList.getTaskCount()));
        } catch (IOException e) {
            ui.displayError(e.getMessage());
        }

    }
}
