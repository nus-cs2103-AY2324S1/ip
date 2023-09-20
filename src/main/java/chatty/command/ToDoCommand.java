package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.ToDo;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the 'todo' command entered by the user to add a new 'todo' task to the task list.
 * When the user provides a 'todo' command with a task description, an instance of this class is created
 * to add the specified 'todo' task to the task list.
 */
public class ToDoCommand extends Command {

    private final Task task;

    /**
     * Constructor for the ToDoCommand instance.
     *
     * @param taskDescription The description of the 'todo' task to be added to the list.
     */
    public ToDoCommand(String taskDescription) {
        super(false);
        this.task = new ToDo(taskDescription);
    }

    /**
     * Executes the 'todo' command to add a new 'todo' task to the task list.
     * This method adds the 'todo' task to the task list, updates the storage (if required),
     * and returns a string confirming the addition of the task.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class for updating the stored task list (if required).
     * @return A String containing a confirmation message for the user.
     * @throws ChattyException If storage encounters an error while saving the task list.
     */

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {
        taskList.addTask(this.task);

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showAdded(this.task, taskList);
    }
}
