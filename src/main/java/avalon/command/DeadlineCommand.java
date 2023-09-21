package avalon.command;

import avalon.AvalonException;
import avalon.task.Deadline;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 * This command is triggered by user input starting with "deadline".
 */
public class DeadlineCommand extends Command {

    private final String userInput;

    /**
     * Constructs a DeadlineCommand with the user input.
     *
     * @param userInput The user input containing the description and deadline.
     */
    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the DeadlineCommand, adding a deadline task to the task list.
     *
     * @param taskList The TaskList to which the deadline task will be added.
     * @param storage  The Storage instance for reading/writing task data.
     * @param ui       The Ui instance for user interaction.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If there is an error while executing the command, such as invalid input.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length != 2) {
            throw new AvalonException("Please provide a description and a deadline "
                    + "(use /by).");
        }
        String description = parts[0];
        String by = parts[1];

        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        ui.showAddTaskMessage(taskList);
        return ui.getOutput();
    }
}
