package command;

import duke.Storage;
import duke.Ui;

import task.Event;
import task.TaskList;

import java.time.LocalDateTime;

/**
 * Adds an event, which has a start date/time and an end date/time, to the todo list
 */
public class EventCommand extends Command {

    /** Description of the task */
    protected String description;

    /** Starting date/time of the event */
    protected LocalDateTime from;

    /** Ending date/time of the event */
    protected LocalDateTime to;
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:\n";

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Adds the event task to the given TaskList, and saves the current TaskList in
     * the specified Storage file
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.from, this.to);
        tasks.addTask(event);
        storage.writeToFile(tasks.getList());
        ui.showMessage(MESSAGE_SUCCESS + "     " + event
                + "\n Now you have " + tasks.getSize() + " tasks in the list");
    }
}
