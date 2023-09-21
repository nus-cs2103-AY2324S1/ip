package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.Task;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command is triggered by user input starting with "delete".
 */
public class DeleteCommand extends Command {

    private final String userInput;

    /**
     * Constructs a DeleteCommand with the user input.
     *
     * @param userInput The user input indicating which task to delete.
     */
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the DeleteCommand, removing a task from the task list.
     *
     * @param taskList The TaskList from which the task will be deleted.
     * @param storage  The Storage instance for reading/writing task data.
     * @param ui       The Ui instance for user interaction.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If there is an error while executing the command, such as an invalid task number.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task deletedTask = taskList.get(taskIndex);
            taskList.removeTask(taskIndex);
            ui.showDeleteTaskMessage(taskList, deletedTask);
            return ui.getOutput();
        } else {
            throw new AvalonException("Invalid task number to be deleted.");
        }
    }
}
