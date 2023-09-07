package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
import Tasks.Event;
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
    }

    public Event getEvent() {
        return this.event;
    }

    /**
     * {@inheritDoc}
     * Executes the command to add an event to the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.event);
        ui.printTaskAddedMessage(this.event, tasks);
    }
}
