package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to mark a task as done.
 * This command is triggered by user input "mark".
 */
public class MarkCommand extends Command {

    private final String userInput;

    /**
     * Constructs a MarkCommand with the user input.
     *
     * @param userInput The user input specifying the task to be marked as done.
     */

    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the MarkCommand to mark a specified task as done.
     *
     * @param taskList The TaskList containing tasks.
     * @param storage  Not used in this command.
     * @param ui       The Ui instance for displaying the result of the command.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If the provided task index is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        boolean isValidIndex = taskIndex >= 0 && taskIndex < taskList.size();
        if (isValidIndex) {
            taskList.get(taskIndex).markDone();
            ui.showMarkMessage(taskList, taskIndex);
            return ui.getOutput();
        } else {
            throw new AvalonException("Invalid task number to be marked.");
        }
    }
}
