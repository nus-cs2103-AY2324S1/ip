package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.Event;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the event command entered by the user to add an event task.
 * When the user provides an event command, an instance of this class is created
 * to add an event task to the task list.
 */
public class EventCommand extends Command {

    private final Task task;

    /**
     * Constructor for the EventCommand instance.
     * Initializes the instance with the event task description, start time, and end time.
     *
     * @param taskDescription The description of the event task.
     * @param startTime The timing at which the event starts.
     * @param endTime The timing at which the event ends.
     */
    public EventCommand(String taskDescription, String startTime, String endTime) {
        super(false);
        this.task = new Event(taskDescription, startTime, endTime);
    }

    /**
     * Executes the event command to add an event task to the task list.
     * This method adds the new event task to the task list, updates the storage,
     * and returns a message indicating that the event task has been added successfully.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class that is responsible for updating stored data.
     * @return A String indicating that the event task has been added successfully.
     * @throws ChattyException If storage encounters an issue while saving the task list.
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
