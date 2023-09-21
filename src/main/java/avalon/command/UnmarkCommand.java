package avalon.command;

import avalon.AvalonException;
import avalon.task.TaskList;
import avalon.utility.Storage;
import avalon.utility.Ui;

/**
 * Represents a command to unmark a task as not done.
 * This command is triggered by user input "unmark".
 */
public class UnmarkCommand extends Command {

    private final String userInput;

    /**
     * Constructs an UnmarkCommand with the user input.
     *
     * @param userInput The user input specifying the task to be unmarked.
     */
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the UnmarkCommand to mark a task as not done in the task list.
     *
     * @param taskList The TaskList containing tasks.
     * @param storage  Not used in this command.
     * @param ui       The Ui instance for displaying the result of the command.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If the task index provided is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        boolean isValidIndex = taskIndex >= 0 && taskIndex < taskList.size();
        if (isValidIndex) {
            taskList.get(taskIndex).markNotDone();
            ui.showUnmarkMessage(taskList, taskIndex);
            return ui.getOutput();
        } else {
            throw new AvalonException("Invalid task number to be unmarked.");
        }
    }
}
