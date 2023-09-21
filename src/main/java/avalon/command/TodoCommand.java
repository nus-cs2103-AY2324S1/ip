package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.task.ToDo;
import avalon.utility.Ui;

/**
 * Represents a command to add a todo task.
 * This command is triggered by user input "todo".
 */
public class TodoCommand extends Command {

    private final String userInput;

    /**
     * Constructs a TodoCommand with the user input.
     *
     * @param userInput The user input specifying the todo task to be added.
     */
    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the TodoCommand to add a todo task to the task list.
     *
     * @param taskList The TaskList containing tasks.
     * @param storage  Not used in this command.
     * @param ui       The Ui instance for displaying the result of the command.
     * @return A string message indicating the result of the command's execution.
     * @throws AvalonException If the description of the todo task is empty.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new AvalonException("The description of a todo cannot be empty.");
        }

        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        ui.showAddTaskMessage(taskList);
        return ui.getOutput();
    }
}
