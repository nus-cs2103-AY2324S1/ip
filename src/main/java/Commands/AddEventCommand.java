package Commands;
import Duke.DukeException;
import OOP.Storage;
import OOP.TaskList;
import OOP.Ui;
import Tasks.Event;

/**
 * The command which encapsulates the data for adding an Event.
 */
public class AddEventCommand implements Command {
    /** The event to be added to the TaskList upon execution.*/
    private Event event;

    /**
     * Constructs a command that adds the encapsulated event to the TaskList upon execution.
     *
     * @param eventName
     * @param eventStart
     * @param eventEnd
     */
    public AddEventCommand(String eventName, String eventStart, String eventEnd) {
        this.event = new Event(eventName, false, eventStart, eventEnd);
        assert !this.event.isDone();
    }

    public Event getEvent() {
        return this.event;
    }

    /**
     * {@inheritDoc}
     * Executes the command to add an event to the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.event.getName().length() == 0) {
            throw new DukeException("Empty Description");
        }

            tasks.addTask(this.event);
        return ui.getTaskAddedMessage(this.event, tasks);
    }
}
