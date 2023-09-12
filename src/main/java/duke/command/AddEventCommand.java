package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Event;

import java.time.LocalDateTime;

/**
 * Represents the command of adding an event to the task list.
 */
public class AddEventCommand extends Command {
    /** Description of the event task. */
    private String description;
    /** Start Time of the event in LocalDateTime. */
    private LocalDateTime startTime;
    /** End Time of the event in LocalDateTime. */
    private LocalDateTime endTime;
    /** Start time of the event represented by a String. */
    private String start;
    /** String representation of the end time for the event. */
    private String end;

    /**
     * Constructor when the start time and end time are represented in the proper LocalDateTime.
     * 
     * @param description Description of the Event.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     */
    public AddEventCommand(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor when the start time and end time are represented in Strings.
     * 
     * @param description Description of the event.
     * @param start Start time of the event.
     * @param end End time of the event.
     */
    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event;
        if (startTime == null && endTime == null) {
            event = new Event(description, start, end);
        } else {
            event = new Event(description, startTime, endTime);
            assert (event != null) : "event not created";
        }
        taskList.add(event);
        ui.addToListSuccess(event, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
