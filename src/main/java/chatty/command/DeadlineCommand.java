package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Deadline;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the deadline command entered by the user.
 * When the user provides a deadline command, an instance of this class is created to
 * add a deadline task to the task list.
 */
public class DeadlineCommand extends Command {

    private final Task task;

    /**
     * Constructor for the deadline command instance.
     * Initializes the instance with the provided task description and deadline.
     *
     * @param taskDescription The description of the deadline task to be added.
     * @param deadline The deadline associated with the task.
     */
    public DeadlineCommand(String taskDescription, String deadline) {
        super(false);
        this.task = new Deadline(taskDescription, deadline);
    }

    /**
     * Executes the deadline command to add a deadline task to the task list.
     * This method adds the specified deadline task to the task list, updates the storage,
     * and returns a message confirming the task addition.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class that is responsible for updating stored data.
     * @return A String indicating that the deadline task has been added successfully.
     * @throws ChattyException If storage encounters an issue while saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {
        taskList.addTask(this.task);

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save task!");
        }
        return ui.showAdded(this.task, taskList);
    }
}
