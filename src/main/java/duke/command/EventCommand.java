package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

/**
 * A command class to add event to tasklist.
 */
public class EventCommand extends Command {
    private String location;
    private String description;
    private LocalDate fromTime;
    private LocalDate toTime;

    /**
     * Constructs a Event object, with time from fromTime to toTime and the given description.
     *
     * @param description Description of the event.
     * @param fromTime    Start time of event.
     * @param toTime      End Time of event.
     */
    public EventCommand(String description, LocalDate fromTime, LocalDate toTime) {
        // the inputs should not be empty
        assert description != null : "description should not be empty";
        assert fromTime != null : "fromTime should not be empty";
        assert toTime != null : "toTime should not be empty";
        this.description = description;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Constructs a Event object, with time from fromTime to toTime, with description and location.
     *
     * @param description Description of the event.
     * @param fromTime    Start time of event.
     * @param toTime      End Time of event.
     * @param location    Location of event.
     */
    public EventCommand(String description, String location, LocalDate fromTime, LocalDate toTime) {
        // the inputs should not be empty
        assert description != null : "description should not be empty";
        assert fromTime != null : "fromTime should not be empty";
        assert toTime != null : "toTime should not be empty";
        this.description = description;
        this.location = location;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Executes the command. Adds a todo task to the tasks TaskList.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(description, this.location, this.fromTime, this.toTime);
        tasks.addTask(task);
        ui.display("    Added Event to the list of tasks:");
        ui.display("    " + task);
        ui.display("    You currently have " + tasks.getSize() + " tasks in the list.");
        try {
            storage.save(tasks.saveToStorage());
        } catch (IOException e) {
            throw new DukeException(
                    "Something went wrong while trying to save the tasks to the disk!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof EventCommand)) {
            return false;
        }

        EventCommand o = (EventCommand) other;
        return this.description.equals(o.description) && this.fromTime.equals(o.fromTime)
                && this.toTime.equals(o.toTime);
    }
}
