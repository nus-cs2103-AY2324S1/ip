package command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import task.Event;
import task.TaskList;

/**
 * Adds an event, which has a start date/time and an end date/time, to the todo list
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = "Got it. I've added this task:\n";

    /** Description of the task */
    protected String description;

    /** Starting date/time of the event */
    protected LocalDateTime from;

    /** Ending date/time of the event */
    protected LocalDateTime to;

    /**
     * Creates an add event command with the given description, start date/time and end date/time
     *
     * @param description description of the task
     * @param from starting date/time of the task
     * @param to ending date/time of the task
     */
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.from, this.to);
        tasks.addTask(event);
        storage.writeToFile(tasks.getList());
        String response = MESSAGE_SUCCESS + event
                + "\nNow you have " + tasks.getSize() + " tasks in the list";
        return response;
    }
}
