package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

import duke.exception.DukeException;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private String description;
    private String startDateTimeStr;
    private String endDateTimeStr;

    /**
     * Constructs an EventCommand with the given event details.
     *
     * @param description     The description of the event.
     * @param startDateTimeStr The start date and time of the event as a string.
     * @param endDateTimeStr   The end date and time of the event as a string.
     */
    public EventCommand(String description, String startDateTimeStr,String endDateTimeStr) {
        this.description = description;
        this.startDateTimeStr = startDateTimeStr;
        this.endDateTimeStr = endDateTimeStr;
    }

    /**
     * Executes the EventCommand by adding a new Event task to the task list,
     * displaying a completion message, and saving the tasks to storage.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If there's an error during command execution.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, startDateTimeStr, endDateTimeStr);
        taskList.add(event);
        ui.displayCompletionMessage(event, taskList.size());
        storage.saveTasksToFile(taskList);
    }

}
