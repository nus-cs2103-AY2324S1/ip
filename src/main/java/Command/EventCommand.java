package command;
import duke.DukeException;
import storage.Storage;
import task.Event;
import taskList.TaskList;
import ui.Ui;

/**
 * The `EventCommand` class represents a command to add an event task to the task list in the Duke application.
 * It extends the `Command` class and is responsible for creating a new event task with the specified details,
 * adding it to the task list, and saving the updated task list to storage.
 * This class encapsulates the behavior of adding an event task to the task list.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new `EventCommand` instance with the specified event details.
     *
     * @param description The description of the event task.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the "Event" command by creating an event task, adding it to the task list,
     * and saving the updated list to storage. It also displays a message indicating that the task has been added.
     *
     * @param taskList The task list to which the event task should be added.
     * @param ui       The user interface for displaying feedback to the user.
     * @param storage  The storage component for saving the updated task list.
     * @throws DukeException An exception may be thrown if there is an error
     *      executing the command (e.g., storage error).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        ui.showTaskAdded(event, taskList.getTaskCount());
        storage.saveTask(taskList.getTasks());
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return `false` because the "Event" command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
